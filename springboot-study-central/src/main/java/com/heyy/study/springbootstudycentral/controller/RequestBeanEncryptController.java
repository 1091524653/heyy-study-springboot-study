package com.heyy.study.springbootstudycentral.controller;

import com.heyy.study.springbootstudycentral.been.CustResponseBody;
import com.heyy.study.springbootstudycentral.been.Person;
import com.heyy.study.springbootstudycentral.constants.RegExpConstants;
import com.heyy.study.springbootstudycentral.utility.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;


@RestController
@RequestMapping(value = "/api/${application.api_version}/heyy")
@Slf4j
public class RequestBeanEncryptController {

    @PostMapping(value = "/encrypt/request",consumes = "application/json",produces = "application/json")
    public ResponseEntity requestValid(@RequestBody Person person){
        log.info("RequestBeanValidController.requestValid request data is:{}", EncryptUtil.encryptToLog(person));
        Person encryptPerson = EncryptUtil.encrypt(person);
        Person decryptPerson = EncryptUtil.dencrypt(encryptPerson);
        String responseCode = "008";
        String description = "encrypt method test is not ok";
        if(!encryptPerson.getIdNo().equals(person.getIdNo())&&decryptPerson.getIdNo().equals(person.getIdNo())){
            responseCode = "000";
            description = "encrypt method test is ok";
        }
        CustResponseBody responseBody = new CustResponseBody();
        responseBody.setResponseCode(responseCode);
        responseBody.setDescription(description);
        ResponseEntity<CustResponseBody> response = new ResponseEntity<>(responseBody,HttpStatus.OK);
        return  response;
    }

}
