package com.gcit.lms.LMSEureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class LmsEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LmsEurekaApplication.class, args);
	}
}
