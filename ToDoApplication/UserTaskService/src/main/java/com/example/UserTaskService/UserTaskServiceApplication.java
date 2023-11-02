package com.example.UserTaskService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class UserTaskServiceApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(UserTaskServiceApplication.class, args);
	}

}
