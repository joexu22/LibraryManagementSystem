package com.gcit.lms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Branch;
import com.gcit.lms.repositories.AuthorRepository;
import com.gcit.lms.repositories.BookRepository;
import com.gcit.lms.repositories.BranchRepository;

@RestController
public class AdminService {
	
	@Autowired
	AuthorRepository authorRepo;
	
	@Autowired
	BookRepository bookRepo;
	
	@Autowired
	BranchRepository branchRepo;

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
	public String saveBranch(@RequestBody Branch branch) {
		String returnString = "";
		try {
			branchRepo.save(branch);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnString;
	}
	
	@RequestMapping(value = "/lms/deleteBranch", method = RequestMethod.GET)
	public void deleteBranch(@RequestParam int branchId) {
		branchRepo.deleteById(branchId);
	}

	@RequestMapping(value = "/lms/readAllAuthors", method = RequestMethod.GET, produces = "application/json")
	public List<Author> readAllAuthors(@RequestParam String searchString) {
		List<Author> authors = new ArrayList<>();
		try {
			if (!searchString.isEmpty()) {
				authors = authorRepo.readAuthorsByName(searchString);
			} else {
				authors = (List<Author>) authorRepo.findAll();
			}
//			for (Author a : authors) {
//				a.setBooks(bdao.getBooksByAuthorID(a.getAuthorId()));
//			}
			return authors;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
	
	
//	@RequestMapping(value = "/lms/readAuthorsByName/{searchString}", method = RequestMethod.GET, produces = "application/json")
//	@ResponseBody
//	public List<Author> readAuthorsByName(@PathVariable("searchString") String searchString) {
//		try {
//			List<Author> authors = authorRepo.readAllAuthorsByName(searchString);
//			for (Author a : authors) {
//				a.setBooks(bdao.getBooksByAuthorID(a.getAuthorId()));
//			}
//			return authors;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

	@RequestMapping(value = "/lms/saveAuthor", method = RequestMethod.POST, consumes = "application/json")
	public String saveAuthor(@RequestBody Author author) {
		String returnString = "";
		try {
			authorRepo.save(author);
//			if (author.getAuthorId() != null && author.getAuthorName() != null) {
//				authorRepo.save(author);
//				returnString = "Auther updated sucessfully";
//			} else if (author.getAuthorId() != null) {
//				adao.deleteAuthor(author);
//				returnString = "Auther deleted sucessfully";
//			} else {
//				adao.addAuthor(author);
//				returnString = "Auther saved sucessfully";
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnString;
	}

}
