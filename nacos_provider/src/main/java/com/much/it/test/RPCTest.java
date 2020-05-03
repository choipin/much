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
import java.security.spec.X509EncodedKeySpec;
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
        //test1();
        //test2();
        //test3();
    }

    private static void test3() {
        String phone = "13788888888";
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJ1c2VySW5mbyI6eyJpZCI6NDYzMzg1Njk4Njc4ODY1OTk0LCJ1c2VybmFtZSI6InBhc3N3b3JkMTIzNDU2IiwicGFzc3dvcmQiOm51bGwsIm5hbWUiOiJjMjNiNWRkMS0xMjY1LTQ2ZjUtODI5NS1iZTZjNGI3MmRkNWIiLCJhZ2UiOjY2LCJzZXgiOjEsImJpcnRoZGF5IjoxNTg4NDA5MzUzMDAwLCJub3RlIjoiMmYyNzk1NmMtNDcwYS00NzUwLTlmY2YtZDQ5Yjk2NDkxZGZiIiwiY3JlYXRlVGltZSI6MTU4ODQwOTM1MzAwMCwidXBkYXRlVGltZSI6MTU4ODQwOTM1MzAwMH19.ACtMOnr_SnGjkaSDyIXx8xakXuiHLWFlYAzbCo_s3qfXkSpzwVDW7sY4bTSQAzrVVLHWQGcOolDXIJgyMpQd4Q";
        Map userInfo = Jwts.parser().setSigningKey(phone).parseClaimsJws(token).getBody().get("userInfo", Map.class);
        System.out.println(userInfo);
    }

    private static void test2() throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnZ60Cnln6CqnNu4AiAYK46DVz2I2OmHjyk8TK4+il6V9l8masqbYhOn/kgMftv9Cj2NV/Gbxe//0Djvu9MteGLPUnJ1CUGEGw8zcsJRgEAO/cx+QIOTGLB38bHh3nE2kCr+/Sh5jmrpLdOvs0XGWhB8lLohqYTkaxO2ha5vMhR5+bFLrAmGq+S8wVn4f0B+KLRdkLI27iQrv/EqkkMi/TkcJs7/PhH+bEz5lGSVHMBdni2UKsIqET1ooHn2rxvltEVt2RySG8wEScyVXivlByJTcceyMC/oakgJCJChxpJI4RFUwRMKFkz6YpOqt8j6IwRVivlPr4byzRPz4wvSsFQIDAQAB";
        byte[] decodeBase64 = Base64.decodeBase64(publicKey);
        //PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(decodeBase64);
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(decodeBase64);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey1 = keyFactory.generatePublic(x509EncodedKeySpec);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE,publicKey1);
        byte[] bytes = cipher.doFinal("password123456".getBytes());
        String rsaPassword = Base64.encodeBase64String(bytes);
        System.out.println(rsaPassword);
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
