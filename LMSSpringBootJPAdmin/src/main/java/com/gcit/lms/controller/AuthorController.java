//package com.gcit.lms.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.gcit.lms.entity.Author;
//import com.gcit.lms.repositories.AuthorRepository;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//
//@RestController
//public class AuthorController {
//
//	@Autowired
//	AuthorRepository authorRepo;
//	
//	@GetMapping(value="/authors/{authorId}")
//	public Author getAuthorById(@RequestParam Integer authorId) {
//		return authorRepo.getOne(authorId);
//	}	
//}
