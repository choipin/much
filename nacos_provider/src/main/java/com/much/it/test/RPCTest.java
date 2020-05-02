package com.much.it.test;

import com.much.it.entity.User;
import com.much.it.service.UserService;
import groovy.util.logging.Slf4j;
import io.jsonwebtoken.Jwts;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.DataInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Map;

/**
 * @description:
 * @project: much
 * @date: 2020/5/2
 * @author: Wenxin
 * @version: 1.0
 */
@Slf4j
public class RPCTest {
    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {
        test1();
        test2();
        test3();
    }

    private static void test3() {
        String phone = "13714189246";
        String token = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC1Hn/Mfw9kX8eeaHt/mgyNsR4JjaXEerfAuxdf25ev30KxrWP66MaeJp5yEkejcyfYpw6/cqNClQpCl9IvIDQxw7Qil6cjCrWX4gENy9NE0FepIWv7Esdhlf31iMWQgMEFKPBO+HUqNU7qvrBKsHfgHxm9hVIwbOucqtOkTmiHQwIDAQAB";
        Map userInfo = Jwts.parser().setSigningKey(phone).parseClaimsJws(token).getBody().get("userInfo", Map.class);
        System.out.println(userInfo);
    }

    private static void test2() throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        String publicKey = "ssdefdsfaa";
        byte[] decodeBase64 = Base64.decodeBase64(publicKey);
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(decodeBase64);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey1 = keyFactory.generatePublic(pkcs8EncodedKeySpec);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE,publicKey1);
        byte[] bytes = cipher.doFinal("password123456".getBytes());
        String rsaPassword = Base64.encodeBase64String(bytes);
        System.out.printf("加密过的密yygy", rsaPassword);
    }

    private static void test1() {
        UserService userServiceProxy = (UserService) Proxy.newProxyInstance(UserService.class.getClassLoader(), new Class[]{UserService.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Socket socket = new Socket();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.writeObject(method);
                objectOutputStream.writeObject(method.getParameterTypes());
                objectOutputStream.writeObject(args);
                objectOutputStream.flush();

                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                String message = dataInputStream.readUTF();
                int id = dataInputStream.readInt();
                return User.builder().id(Long.valueOf(id+""));
            }
        });
    }
}
