package com.heyy.study.springbootstudycentral.config;

import com.heyy.study.springbootstudycentral.interceptor.MyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Classname InterceptorConfig
 * @Description config interceptor
 * @Date 2019/5/13 20:42
 * @Created by Breeze
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter{

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getMyInterceptor()).addPathPatterns("/api/v1/heyy/valid/request");
        super.addInterceptors(registry);
    }

    @Bean
    public HandlerInterceptor getMyInterceptor(){
        return new MyInterceptor();
    }
}
