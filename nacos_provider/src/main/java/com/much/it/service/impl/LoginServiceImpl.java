package com.much.it.service.impl;

import com.much.it.entity.User;
import com.much.it.mapper.UserMapper;
import com.much.it.service.LoginService;
import groovy.util.logging.Slf4j;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * @description:
 * @project: much
 * @date: 2020/5/2
 * @author: Wenxin
 * @version: 1.0
 */
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Map<String, String> verification(String phone) {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            String privateKey = Base64.encodeBase64String(keyPair.getPrivate().getEncoded());
            String publicKey = Base64.encodeBase64String(keyPair.getPublic().getEncoded());
            String redisKey = String.format("user:login:phone:%s",phone);
            redisTemplate.opsForValue().set(redisKey,privateKey,60*30, TimeUnit.SECONDS);
            HashMap<String, String> map = new HashMap<>();
            map.put("phone",phone);
            map.put("publicCode",publicKey);
            return map;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String login(Map<String,String> map) {
        try {
            String rsaPassword =  map.get("password");
            String username =  map.get("username");
            String phone =  map.get("phone");
            String redisKey = String.format("user:login:phone:%s",phone);
            String privateKey = (String) redisTemplate.opsForValue().get(redisKey);
            if (StringUtils.isEmpty(privateKey)){
                throw new RuntimeException("privateKye is validate");
            }
            //X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.decodeBase64(privateKey));
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            PrivateKey privateKey1 = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE,privateKey1);
            byte[] bytes = cipher.doFinal(Base64.decodeBase64(rsaPassword));
            String password = new String(bytes, StandardCharsets.UTF_8);
            List<User> users = userMapper.findUserByPhoneAndPassword(username,password);
            if (CollectionUtils.isEmpty(users)){
                throw new RuntimeException("password or phone is not right");
            }
            //transaface token
            HashMap<String, Object> claims = new HashMap<>();
            claims.put("userInfo", users.get(0));
            String token = Jwts.builder().signWith(SignatureAlgorithm.HS512, phone).setClaims(claims).compact();
            return token;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }
}
