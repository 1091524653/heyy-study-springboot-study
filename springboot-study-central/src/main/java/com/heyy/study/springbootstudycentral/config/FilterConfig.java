package com.heyy.study.springbootstudycentral.config;

import com.heyy.study.springbootstudycentral.filter.RequestBodyCacheFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Classname FilterConfig
 * @Description config filter
 * @Date 2019/5/13 20:14
 * @Created by Breeze
 */
@Configuration
@Slf4j
public class FilterConfig {

    @Bean
    public RequestBodyCacheFilter requestBodyCacheFilter(){
        log.info("Registering Request body caching filter");
        return new RequestBodyCacheFilter();
    }
}
