package com.gcit.lms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Branch;
import com.gcit.lms.repositories.BookRepository;
import com.gcit.lms.repositories.BranchRepository;

@RestController
public class LibrarianService {
	
	@Autowired
	BranchRepository branchRepo;

	@Autowired
	BookRepository bookRepo;
	
	@RequestMapping(value = "/lms/readBranchs", method = RequestMethod.GET, produces = "application/json")
	public List<Branch> readBranch() {
		List<Branch> branchs = new ArrayList<>();
		try {
			branchs = branchRepo.findAll();
			return branchs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/lms/saveBranch", method = RequestMethod.POST, consumes = "application/json")
	public String saveAuthor(@RequestBody Branch branch) {
		String returnString = "";
		try {
			branchRepo.save(branch);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnString;
	}

	@RequestMapping(value = "/lms/readAllBooks", method = RequestMethod.GET, produces = "application/json")
	public List<Book> readAllBooks() {
		try {
				return bookRepo.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	
}
