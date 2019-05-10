package com.heyy.study.springbootstudycentral.controller;

import com.google.common.util.concurrent.RateLimiter;
import com.heyy.study.springbootstudycentral.been.CustResponseBody;
import com.heyy.study.springbootstudycentral.been.Person;
import com.heyy.study.springbootstudycentral.thread.RateLimiterThread;
import com.heyy.study.springbootstudycentral.utility.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@RestController
@RequestMapping(value = "/api/${application.api_version}/heyy")
@Slf4j
public class RateLimiterController {

    @PostMapping(value = "/limit",consumes = "application/json",produces = "application/json")
    public ResponseEntity requestValid(@RequestParam(value = "limit",required = false) String limit){
        RateLimiter rateLimiter = RateLimiter.create(1);
        List<Runnable> tasks = new ArrayList<Runnable>();
        for (int i = 0; i < 10; i++) {
            tasks.add(new RateLimiterThread(i));
        }
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (Runnable runnable : tasks) {
            if("true".equals(limit)){
                System.out.println("等待时间：" + rateLimiter.acquire());
            }
            threadPool.execute(runnable);
        }
        CustResponseBody responseBody = new CustResponseBody();
        responseBody.setResponseCode("000");
        responseBody.setDescription("successful");
        ResponseEntity<CustResponseBody> response = new ResponseEntity<>(responseBody,HttpStatus.OK);
        return  response;
    }

}
