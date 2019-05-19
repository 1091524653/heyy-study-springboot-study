package com.heyy.study.datasource.utility;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Classname PropertiesLoadUtility
 * @Description TODO
 * @Date 2019/5/11 09:16
 * @Created by Breeze
 */
@Slf4j
public class PropertiesLoadUtility {
    private Properties properties = new Properties();

    public String getValueByKey(String path,String fileName,String key){
        String result = "";
        InputStream inputStream = this.getClass().getResourceAsStream(path.concat(fileName));
        try {
            properties.load(inputStream);
            result = properties.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
