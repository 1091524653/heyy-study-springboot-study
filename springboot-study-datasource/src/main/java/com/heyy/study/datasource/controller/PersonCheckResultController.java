package com.heyy.study.datasource.controller;

import com.heyy.study.datasource.utility.PropertiesLoadUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname PersonCheckResultController
 * @Description return person check result
 * @Date 2019/5/10 23:35
 * @Created by Breeze
 */
@RestController
@RequestMapping(value = "/api/${application.api_version}/datasource")
@Slf4j
public class PersonCheckResultController {

    @PostMapping(value = "/person/check",consumes = "application/json",produces = "application/json")
    public ResponseEntity personCheck(Object person){
        log.info("PersonCheckResultController.personCheck method in");
        String path = "/datasource/dummy/";
        String fileName = "dummy-response.properties";
        String key = "person.check.result";
        String checkResult = new PropertiesLoadUtility().getValueByKey(path, fileName, key);
        ResponseEntity<Object> response = new ResponseEntity<>(checkResult, HttpStatus.OK);
        return  response;
    }

    /*public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(responseBody);
        System.out.println(result);
    }*/
}
