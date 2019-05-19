package com.heyy.study.springbootstudycentral.utility;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.heyy.study.springbootstudycentral.been.DencryptSensitiveDataSerializer;
import com.heyy.study.springbootstudycentral.been.EncryptSensitiveDataSerializer;
import com.heyy.study.springbootstudycentral.exception.CustomException;
import com.heyy.study.springbootstudycentral.exception.ExceptionEnum;

import java.util.Objects;

/**
 * @Classname EncryptUtil
 * @Description TODO
 * @Date 2019/5/10 13:51
 * @Created by Breeze
 */
public class EncryptUtil {
    private static String encryptKey;
    private static AesUtil aesUtil;

    public static void init(String key){
        encryptKey = key;
        aesUtil = new AesUtil(encryptKey);
    }

    public static <T> T encrypt(T obj){
        Objects.requireNonNull(obj, "obj must not be null");
        return encrypt(obj,ObjectUtil.getType(obj.getClass()));
    }

    public static <T,W,I> T encrypt(T obj,Class<W> wClass,Class<I> iClass){
        Objects.requireNonNull(obj, "obj must not be null");
        return encrypt(obj,ObjectUtil.getParametricType(wClass,iClass));
    }

    public static <T> String encryptToLog(T obj){
        return ObjectUtil.objectToJson(encrypt(obj));
    }

    public static <T> T encrypt(T obj,JavaType type){
        Objects.requireNonNull(obj, "obj must not be null");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false );
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(String.class,new EncryptSensitiveDataSerializer());
        mapper.registerModule(simpleModule);
        T result = ObjectUtil.jsonToObject(ObjectUtil.objectToJson(obj,mapper),type,mapper );
        return result;
    }

    public static <T> T dencrypt(T obj){
        Objects.requireNonNull(obj, "obj must not be null");
        return dencrypt(obj,ObjectUtil.getType(obj.getClass()));
    }

    public static <T,W,I> T dencrypt(T obj,Class<W> wClass,Class<I> iClass){
        Objects.requireNonNull(obj, "obj must not be null");
        return dencrypt(obj,ObjectUtil.getParametricType(wClass,iClass));
    }

    public static <T> T dencrypt(T obj,JavaType type){
        Objects.requireNonNull(obj, "obj must not be null");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false );
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(String.class,new DencryptSensitiveDataSerializer());
        mapper.registerModule(simpleModule);
        T result = ObjectUtil.jsonToObject(ObjectUtil.objectToJson(obj,mapper),type,mapper );
        return result;
    }

    public static String encrypt(String plainText){
        try {
            return aesUtil.encrypt(plainText);
        } catch (Exception e) {
            throw new CustomException(ExceptionEnum.SSC_0005,e.getMessage());
        }
    }

    public static String dencrypt(String encryptedValue){
        try {
            return aesUtil.dencrypt(encryptedValue);
        } catch (Exception e) {
            throw new CustomException(ExceptionEnum.SSC_0006,e.getMessage());
        }
    }

}
