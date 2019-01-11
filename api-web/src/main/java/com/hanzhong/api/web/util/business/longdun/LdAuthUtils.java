package com.hanzhong.api.web.util.business.longdun;

import com.quantum.auth.Authcode;
import com.quantum.auth.RSA2048;
import com.quantum.auth.RandomTools;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;

/**
 * 加密解密工具类
 */
public class LdAuthUtils {

    static RSA2048 rsa2048;
    static Authcode authcode;
    private static final String CRYPT_KEY = "key";
    private static final String CRYPT_VALUE = "value";
    public static final String UTF_8 = "UTF-8";

    /**
     * 获取RSA2048
     *
     * @return RSA2048
     */
    public static RSA2048 getRsa2048() {
        if (rsa2048 == null) {
            synchronized (RSA2048.class) {
                if (rsa2048 == null) {
                    rsa2048 = new RSA2048();
                }
            }
        }
        return rsa2048;
    }

    /**
     * 获取Authcode
     *
     * @return Authcode
     */
    public static Authcode getAuthCode() {
        if (authcode == null) {
            synchronized (Authcode.class) {
                if (authcode == null) {
                    authcode = new Authcode();
                }
            }
        }
        return authcode;
    }

    /**
     * 加密
     *
     * @param plainText 明文
     * @param key       Key
     * @return Map
     * @throws Exception
     */
    public static Map<String, Object> encode(String plainText, Key key) throws Exception {
        Map<String, Object> result = new HashMap<>(16);
        // 随机字符串
        String random = RandomTools.generateRandom();
        // RSA加密
        String encrypt = getRsa2048().encode(random, key);
        result.put(CRYPT_KEY, encrypt);

        // url编码
        plainText = URLEncoder.encode(plainText, UTF_8);
        // DES加密
        String value = getAuthCode().encode(plainText, random);
        result.put(CRYPT_VALUE, value);

        return result;
    }

    /**
     * 解密
     *
     * @param cipherText 密文
     * @param key        Key
     * @return String
     * @throws Exception
     */
    public static String decode(Map<String, Object> cipherText, Key key) throws Exception {
        String keyStr = cipherText.get(CRYPT_KEY).toString();
        String value = cipherText.get(CRYPT_VALUE).toString();
        // RSA解密
        keyStr = getRsa2048().decode(keyStr, key);
        // DES解密
        value = getAuthCode().decode(value, keyStr);
        // url解码
        value = URLDecoder.decode(value, UTF_8);
        return value;
    }

}
