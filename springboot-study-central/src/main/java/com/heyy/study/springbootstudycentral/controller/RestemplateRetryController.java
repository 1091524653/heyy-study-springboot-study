package com.heyy.study.springbootstudycentral.controller;

import com.heyy.study.springbootstudycentral.been.CustResponseBody;
import com.heyy.study.springbootstudycentral.been.Person;
import com.heyy.study.springbootstudycentral.constants.RegExpConstants;
import com.heyy.study.springbootstudycentral.service.RestTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;


@RestController
@RequestMapping(value = "/api/${application.api_version}/heyy")
@Slf4j
@Validated
public class RestemplateRetryController {

    @Autowired
    private RestTemplateService restTemplateService;

    @Value("${datasource.url.protocal}")
    private String protocal;

    @Value("${datasource.url.host}")
    private String host;

    @Value("${datasource.url.port}")
    private String port;

    @Value("${datasource.url.api}")
    private String api;

    @PostMapping(value = "/rest/retry",consumes = "application/json",produces = "application/json")
    public ResponseEntity requestValid( @RequestBody @Validated Person person){
        log.info("RequestBeanValidController.requestValid request data is:{}",person.toString());
        String url = restTemplateService.getUrl(protocal, host, port, api);
        String RestResponse = restTemplateService.postWithHttps(url, person.toString(), null);
        ResponseEntity<String> response = new ResponseEntity<>(RestResponse,HttpStatus.OK);
        return  response;
    }

}
