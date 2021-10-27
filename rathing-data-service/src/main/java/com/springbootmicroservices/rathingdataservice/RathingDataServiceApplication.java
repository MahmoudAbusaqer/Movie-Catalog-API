package com.springbootmicroservices.rathingdataservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RathingDataServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RathingDataServiceApplication.class, args);
	}

}
