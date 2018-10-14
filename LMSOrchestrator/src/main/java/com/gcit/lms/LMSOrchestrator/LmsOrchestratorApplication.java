package com.gcit.lms.LMSOrchestrator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;


@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan("com.gcit.lms")
public class LmsOrchestratorApplication {

//	private static final Logger log = LoggerFactory.getLogger(LmsOrchestratorApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(LmsOrchestratorApplication.class, args);
	}

//	@Bean
//	public RestTemplate restTemplate(RestTemplateBuilder builder) {
//		return builder.build();
//	}

//	@Bean
//	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
//		return args -> {
//			Branch branch = restTemplate.getForObject("http://localhost:8091/lms/branches", Branch.class);
//			log.info(branch.toString());
//		};
//	}
}
