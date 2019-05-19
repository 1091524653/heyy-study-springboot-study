package com.heyy.study.springbootstudycentral.utility;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.Key;

/**
 * @Classname AesUtil
 * @Description TODO
 * @Date 2019/5/10 20:38
 * @Created by Breeze
 */
public class AesUtil {
    public static final String ALGORITHM = "AES";
    public static final String ENCODING_UTF_8 = "UTF-8";
    private String aesKey = null;

    public AesUtil(String aesKey){
        this.aesKey = aesKey;
    }

    private byte[] encrypt(byte[] data,byte[] key) throws Exception {
        Key k = getKey(key);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(1,k );
        return  cipher.doFinal(data);

    }

    public String encrypt(String data) throws Exception {
        return Base64.encodeBase64String(this.encrypt(data.getBytes(ENCODING_UTF_8), this.aesKey.getBytes(ENCODING_UTF_8)));
    }

    private byte[] decrypt(byte[] data,byte[] key)throws Exception{
        Key k = getKey(key);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(2,k );
        return  cipher.doFinal(data);
    }

    public String dencrypt(String data) throws Exception {
        return new String(this.decrypt(Base64.decodeBase64(data),this.aesKey.getBytes(ENCODING_UTF_8) ),ENCODING_UTF_8);
    }

    private Key getKey(byte[] key){
        SecretKeySpec secretKeySpec = new SecretKeySpec(key,ALGORITHM);
        return secretKeySpec;
    }
}
