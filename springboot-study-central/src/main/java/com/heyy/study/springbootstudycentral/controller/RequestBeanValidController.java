package com.heyy.study.springbootstudycentral.controller;

import com.heyy.study.springbootstudycentral.been.CustResponseBody;
import com.heyy.study.springbootstudycentral.constants.RegExpConstants;
import heyy.study.common.been.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@RestController
@RequestMapping(value = "/api/${application.api_version}/heyy")
@Slf4j
@Validated
public class RequestBeanValidController {

    @PostMapping(value = "/valid/request",consumes = "application/json",produces = "application/json")
    public ResponseEntity requestValid(@RequestHeader(value = "staffId",required = true) @Pattern(regexp = RegExpConstants.REGEX_STAFF_ID) String staffId,
                                       @RequestHeader(value = "staffName") @Pattern(regexp = RegExpConstants.REGEX_NAME) String staffName,
                                       @RequestParam(value = "fromDB",required = false) boolean formDB,
                                       @RequestBody @Validated Person person){
        log.info("RequestBeanValidController.requestValid request data is:{}",person.toString());
        CustResponseBody responseBody = new CustResponseBody();
        responseBody.setResponseCode("000");
        responseBody.setDescription("valid request data successful");
        ResponseEntity<CustResponseBody> response = new ResponseEntity<>(responseBody,HttpStatus.OK);
        return  response;
    }

}
