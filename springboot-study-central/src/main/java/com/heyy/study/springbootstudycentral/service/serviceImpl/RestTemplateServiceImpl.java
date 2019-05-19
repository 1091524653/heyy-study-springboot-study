package com.heyy.study.springbootstudycentral.service.serviceImpl;

import com.heyy.study.springbootstudycentral.service.RestTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Classname RestemplateServiceImpl
 * @Description TODO
 * @Date 2019/5/11 09:04
 * @Created by Breeze
 */
@Service
@Slf4j
public class RestTemplateServiceImpl implements RestTemplateService {

    @Qualifier(value = "simpleRestTemplate")
    @Autowired
    RestTemplate restTemplate;

    @Retryable(value = {SocketTimeoutException.class, HttpServerErrorException.class},maxAttempts = 3,backoff = @Backoff(delay = 1000))
    @Override
    public String postWithHttps(String url,String requestBody,Map<String,String> custHeaders){
        List<MediaType> mediaTypeList = new ArrayList<>();
        mediaTypeList.add(MediaType.APPLICATION_JSON_UTF8);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(mediaTypeList);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        if(null!=custHeaders&&!custHeaders.isEmpty()){
            for (Map.Entry<String, String> stringStringEntry : custHeaders.entrySet()) {
                httpHeaders.add(stringStringEntry.getKey(), stringStringEntry.getValue());
            }
        }

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody,httpHeaders);
        log.info("call other party start");
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST,requestEntity,String.class);
        log.info("call other party end");
        String response = "";
        if(null!=responseEntity){
            response = responseEntity.getBody();
        }
        return response;
    }

    @Override
    public String getUrl(String protocal,String host,String port,String api){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(protocal).append(host).append(":").append(port).append(api);
        return stringBuilder.toString();
    }

}
