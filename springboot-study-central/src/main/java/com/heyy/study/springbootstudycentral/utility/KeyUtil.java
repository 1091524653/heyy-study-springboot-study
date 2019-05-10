package com.heyy.study.springbootstudycentral.utility;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Classname KeyUtil
 * @Description TODO
 * @Date 2019/5/10 19:18
 * @Created by Breeze
 */
@Component
public class KeyUtil {
    private static String key1;

    private static String key2;

    private static String key3;

    @Value("${heyy.spingboot.study.encrypt.key1}")
    public static void setKey1(String key1) {
        KeyUtil.key1 = key1;
    }

    @Value("${heyy.spingboot.study.encrypt.key2}")
    public static void setKey2(String key2) {
        KeyUtil.key2 = key2;
    }

    @Value("${heyy.spingboot.study.encrypt.key3}")
    public static void setKey3(String key3) {
        KeyUtil.key3 = key3;
    }

    public String getEncryptKey(){
        String unEncryptPwd = key1+key2+key3;
        String encryptedPwd = DigestUtils.md2Hex(unEncryptPwd);
        return encryptedPwd;
    }

    @PostConstruct
    public void init(){
        String encryptedPwd = new KeyUtil().getEncryptKey();
        EncryptUtil.init(encryptedPwd);
    }
}
