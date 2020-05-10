package com.much.it.test;

import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.Test;

/**
 * @description:
 * @project: much
 * @date: 2020/5/10
 * @author: Wenxin
 * @version: 1.0
 */
public class CryptorTest {
    public static void main(String[] args) {
        cryptor1();
    }

    //-Djasypt.encryptor.password=Y5kScmS3D9mWQ
    @Test
    private static void cryptor1(){
        BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
        basicTextEncryptor.setPassword("Y5kScmS3D9mWQ");
        String root = basicTextEncryptor.encrypt("root");
        System.out.println(root);
        System.out.println(basicTextEncryptor.decrypt(root));
    }
}
