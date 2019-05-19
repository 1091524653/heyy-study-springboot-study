package com.heyy.study.springbootstudycentral.service;

import java.util.Map;

/**
 * @Classname RestTemplateService
 * @Description TODO
 * @Date 2019/5/11 09:04
 * @Created by Breeze
 */
public interface RestTemplateService {
    public String postWithHttps(String url,String requestBody,Map<String,String> custHeaders);
    public String getUrl(String protocal,String host,String port,String api);
}
