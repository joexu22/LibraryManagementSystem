package com.gcit.lms.service;

import java.util.ArrayList;
import java.util.List;

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

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Branch;

@RestController
public class AdminService {	
	@RequestMapping(value = "/lms/branch/{id}", method = RequestMethod.GET, produces = { "application/json", "application/xml" })
	public Branch readAuthor(@PathVariable Integer id) {
		RestTemplate restTemplate = new RestTemplate();
		try {
			Branch branch = restTemplate.getForObject("http://localhost:8091/lms/branches/" + id, Branch.class);
			return branch;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
