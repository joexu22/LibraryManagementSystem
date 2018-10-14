package com.gcit.lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.gcit.lms.entity.Branch;

@RestController
public class LibrarianService {
//	@RequestMapping(value = "/lms", method = RequestMethod.GET, produces = { "application/json", "application/xml" })
//	public Branch readAuthor() {
//		RestTemplate restTemplate = new RestTemplate();
//		try {
//			Branch branch = restTemplate.getForObject("http://localhost:8091/lms/branches/1", Branch.class);
//			return branch;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	@RequestMapping(value = "lms/testing", method = RequestMethod.GET, produces = "application/json")
//	public String testString() {
//		return "Testing";
//	}
}
