package com.much.it.util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @description:
 * @project: much
 * @date: 2020/5/6
 * @author: Wenxin
 * @version: 1.0
 */
public class AesUtil {
    public final static String ECB = "AES/ECB/PKCS5Padding";
    public final static String CBC = "AES/CBC/PKCS5Padding";

    public static String encrypt(String context,String key,String ivKey) {
        try {
            boolean hasIv = ivKey != null;
            Cipher cipher = Cipher.getInstance(hasIv ? CBC : ECB);
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
            if (hasIv) {
                IvParameterSpec ivParameterSpec = new IvParameterSpec(ivKey.getBytes(StandardCharsets.UTF_8));
                cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            } else {
                cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            }
            byte[] bytes = cipher.doFinal(context.getBytes(StandardCharsets.UTF_8));
            String s = Base64.getEncoder().encodeToString(bytes);
            return s;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String cipherContext,String key,String ivKey){

            try {
                boolean hasIV = ivKey !=null;
                Cipher cipher = Cipher.getInstance(hasIV ? CBC : ECB);
                SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
                if (hasIV){
                    IvParameterSpec ivParameterSpec = new IvParameterSpec(ivKey.getBytes(StandardCharsets.UTF_8));
                    cipher.init(Cipher.DECRYPT_MODE,secretKeySpec,ivParameterSpec);
                }else {
                    cipher.init(Cipher.DECRYPT_MODE,secretKeySpec);
                }
                byte[] bytes = cipher.doFinal(Base64.getDecoder().decode(cipherContext));
                return new String(bytes, StandardCharsets.UTF_8);
            } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException e) {
                e.printStackTrace();
            }


        return null;
    }

}
