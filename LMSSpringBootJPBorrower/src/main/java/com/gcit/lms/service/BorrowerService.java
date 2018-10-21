package com.gcit.lms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gcit.lms.entity.Borrower;
import com.gcit.lms.repositories.BranchRepository;

@RestController
public class BorrowerService {
	
	@Autowired
	BranchRepository borrowerRepo;

	@RequestMapping(value = "/lms/readBorrowers", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
	public List<Borrower> readBarrowers() {
		List<Borrower> borrowers = new ArrayList<>();
		try {
			borrowers = borrowerRepo.findAll();
			return borrowers;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/lms/saveBorrowers", method = RequestMethod.POST, consumes = "application/json")
	public String saveAuthor(@RequestBody Borrower borrower) {
		String returnString = "";
		try {
			borrowerRepo.save(borrower);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnString;
	}
}
