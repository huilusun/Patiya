/**
 *
 */
package com.huiju.blackbrin.util;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * @author xpchen
 */
public class CryptUtils {

    private static String DEFAULT_HMAC_SHA256_Key = "test";
    //DES 加密密匙为8位
    private static String DEFAULT_DES_KEY = "ABSCDFGH";

    /**
     * Des加密
     *
     * @param content 待加密内容
     * @param key     加密的密钥
     * @return
     */
    public static String encrypt(String content, String key) throws Exception {
        if (null == key || "".equals(key)) {
            key = DEFAULT_DES_KEY;
        }
        SecureRandom random = new SecureRandom();
        DESKeySpec desKey = new DESKeySpec(key.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secureKey = keyFactory.generateSecret(desKey);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, secureKey, random);
        byte[] result = cipher.doFinal(content.getBytes());
        byte[] b = Base64.getEncoder().encode(result);
        return new String(b);
    }

    /**
     * Des解密
     *
     * @param content 待解密内容
     * @param key     解密的密钥
     * @return
     */
    public static String decrypt(String content, String key) throws Exception {
        if (null == key || "".equals(key)) {
            key = DEFAULT_DES_KEY;
        }
        byte[] b = Base64.getDecoder().decode(content.getBytes());
        SecureRandom random = new SecureRandom();
        DESKeySpec desKey = new DESKeySpec(key.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secureKey = keyFactory.generateSecret(desKey);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, secureKey, random);
        byte[] result = cipher.doFinal(b);
        return new String(result);
    }

    /**
     * HMAC_SHA256 摘要加密
     *
     * @param content 需要加密的内容
     * @param key     加密的密匙
     * @return
     * @throws Exception
     */
    public static String HMACSHA256encrypt(String content, String key) throws Exception {
        if (null == key || "".equals(key)) {
            key = DEFAULT_HMAC_SHA256_Key;
        }
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        return Base64.getEncoder().encodeToString(sha256_HMAC.doFinal(content.getBytes()));
    }


    public static void main(String args[]) throws Exception{
        String str=decrypt("b9xF6ttY56C74CtCqmjzcl/OtgPMGm4Z4RoDAupyDEKDaS0nBqtJs12mMIGPbuwapXn3xy2CuxWBUb4cKqMcvru8TnqeumD4NcjzlvTmscrTeMTZGmF8FpGQUE0ruY6K1FqhCP+CP5MN48Ce/sf5oozZI3GvWjpt","");
        String[] content=str.split("\\|");
        System.out.print(str);
    }
}




