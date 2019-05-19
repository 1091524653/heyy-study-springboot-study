package com.heyy.study.springbootstudycentral;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableRetry
@EnableScheduling
public class SpringbootStudyCentralApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootStudyCentralApplication.class, args);
	}

}
