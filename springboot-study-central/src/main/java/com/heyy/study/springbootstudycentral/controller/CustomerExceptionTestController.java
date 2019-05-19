package com.heyy.study.springbootstudycentral.controller;

import com.heyy.study.springbootstudycentral.been.Person;
import com.heyy.study.springbootstudycentral.exception.CustomException;
import com.heyy.study.springbootstudycentral.exception.ExceptionEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/${application.api_version}/heyy")
@Slf4j
public class CustomerExceptionTestController {

    @PostMapping(value = "/customer/exception",consumes = "application/json",produces = "application/json")
    public ResponseEntity requestValid(@RequestBody Person person){
        throw new CustomException(ExceptionEnum.SSC_0002,"customer exception test controller");
    }

}
