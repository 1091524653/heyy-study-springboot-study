package com.heyy.study.springbootstudycentral.controller;

import com.heyy.study.springbootstudycentral.been.CustResponseBody;
import com.heyy.study.springbootstudycentral.been.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/${application.api_version}/heyy")
@Slf4j
public class RequestBeanValidController {

    @PostMapping(value = "/valid/request",consumes = "application/json",produces = "application/json")
    public ResponseEntity requestValid(@RequestBody @Validated Person person){
        CustResponseBody responseBody = new CustResponseBody();
        responseBody.setResponseCode("000");
        responseBody.setDescription("valid request data successful");
        ResponseEntity<CustResponseBody> response = new ResponseEntity<>(responseBody,HttpStatus.OK);
        return  response;
    }

}
