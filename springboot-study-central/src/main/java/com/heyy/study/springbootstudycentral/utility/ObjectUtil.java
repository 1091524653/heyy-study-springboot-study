package com.heyy.study.springbootstudycentral.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.heyy.study.springbootstudycentral.exception.CustomException;
import com.heyy.study.springbootstudycentral.exception.ExceptionEnum;

import java.io.IOException;
import java.util.Objects;

/**
 * @Classname ObjectUtil
 * @Description TODO
 * @Date 2019/5/10 20:01
 * @Created by Breeze
 */
public class ObjectUtil {

    private static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false );
    }

    public static <T> JavaType getType(Class<T> simpleClass){
        return mapper.getTypeFactory().constructType(simpleClass);
    }

    public static <T,V> JavaType getParametricType(Class<T> wrapper,Class<V> dataClass){
        return mapper.getTypeFactory().constructParametricType(wrapper,dataClass);
    }

    public static <T,V> JavaType getParametricType(Class<T> wrapper,JavaType dataType){
        return mapper.getTypeFactory().constructParametricType(wrapper,dataType);
    }

    public static <T> T jsonToObject(String jsonStr,JavaType type,ObjectMapper customMapper) {
        Objects.requireNonNull(jsonStr,"json string is null" );
        try {
            return customMapper.readValue(jsonStr,type );
        } catch (IOException e) {
            throw new CustomException(ExceptionEnum.SSC_0003,e.getMessage());
        }
    }

    public static <T> T jsonToObject(String jsonStr,JavaType type){
        return jsonToObject(jsonStr,type,mapper);
    }

    public static <T,W,I> T jsonToObject(String jsonStr,Class<W> wClass,Class<I> iClass){
        return jsonToObject(jsonStr,getParametricType(wClass, iClass));
    }

    public static <T> T jsonToObject(String jsonStr,Class<T> cls){
        return jsonToObject(jsonStr,getType(cls));
    }

    public static String objectToJson(Object obj,ObjectMapper customMapper){
        Objects.requireNonNull(obj,"obj is null");
        Objects.requireNonNull(customMapper,"customMapper is null");
        try {
            return customMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new CustomException(ExceptionEnum.SSC_0004,e.getMessage());
        }
    }

    public static String objectToJson(Object obj){
        return objectToJson(obj,mapper );
    }
}
