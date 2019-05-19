package com.heyy.study.springbootstudycentral.been;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import com.heyy.study.springbootstudycentral.annotation.SensitiveFieldAnnotation;
import com.heyy.study.springbootstudycentral.utility.EncryptUtil;

import java.io.IOException;

/**
 * @Classname EncryptSensitiveDataSerializer
 * @Description TODO
 * @Date 2019/5/10 21:23
 * @Created by Breeze
 */
public class EncryptSensitiveDataSerializer extends StdSerializer<String> implements ContextualSerializer {

    private static final long serialVersionUID = -1897422003712932300L;

    public EncryptSensitiveDataSerializer(){
        super(String.class);
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        if(beanProperty!=null){
            SensitiveFieldAnnotation sensitiveFieldAnnotation = (SensitiveFieldAnnotation)beanProperty.getAnnotation(SensitiveFieldAnnotation.class);
            if(sensitiveFieldAnnotation !=null){
                return new EncryptSensitiveDataSerializer();
            }
        }
        return new StringSerializer();
    }

    @Override
    public void serialize(String s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(EncryptUtil.encrypt(s));
    }
}
