package com.gcit.lms;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.gcit.lms")
public class LmsBootJpaApplication {

	final static Logger logger = Logger.getLogger(LmsBootJpaApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(LmsBootJpaApplication.class, args);
		logger.warn("This is a Test Log");
	}
}
