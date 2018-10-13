package com.gcit.lms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Branch;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.repositories.AuthorRepository;
import com.gcit.lms.repositories.BookRepository;
import com.gcit.lms.repositories.BorrowerRepository;
import com.gcit.lms.repositories.BranchRepository;
import com.gcit.lms.repositories.GenreRepository;

@RestController
public class AdminService {

	@Autowired
	AuthorRepository authorRepo;

	@Autowired
	BookRepository bookRepo;

	@Autowired
	BranchRepository branchRepo;

	@Autowired
	GenreRepository genreRepo;

	@Autowired
	BorrowerRepository borrowerRepo;

	@GetMapping(value = "/lms/authors", produces = { "application/json", "application/xml" })
	public List<Author> getAllAuthors() {
		return authorRepo.findAll();
	}

	@GetMapping(value = "/lms/authors/{authorId}", produces = { "application/json", "application/xml" })
	public Author getAuthorById(@PathVariable Integer authorId) {
		return authorRepo.getOne(authorId);
	}

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

	@RequestMapping(value = "/lms/deleteAuthor", method = RequestMethod.GET)
	public void deleteAuthor(@RequestParam int authorId) {
		authorRepo.deleteById(authorId);
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

	@RequestMapping(value = "/lms/readAllGenres", method = RequestMethod.GET, produces = "application/json")
	public List<Genre> readAllGenres() {
		try {
			return genreRepo.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/lms/saveGenre", method = RequestMethod.POST, consumes = "application/json")
	public String saveGenre(@RequestBody Genre genre) {
		String returnString = "";
		try {
			genreRepo.save(genre);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnString;
	}

	@RequestMapping(value = "/lms/deleteGenre", method = RequestMethod.GET)
	public void deleteGenre(@RequestParam int genreId) {
		genreRepo.deleteById(genreId);
	}

	@RequestMapping(value = "lms/readAllBorrowers", method = RequestMethod.GET)
	public List<Borrower> readAllBorrowers() {
		try {
			return borrowerRepo.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

//	@RequestMapping(value = "lms/readBorrowerById", method = RequestMethod.GET)
//	public Optional<Borrower> readBorrowerById(@RequestParam int borrowerId) {
//		try {
//			return borrowerRepo.findById(borrowerId);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

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
//				returnString = "Author updated successfully";
//			} else if (author.getAuthorId() != null) {
//				adao.deleteAuthor(author);
//				returnString = "Author deleted successfully";
//			} else {
//				adao.addAuthor(author);
//				returnString = "Author saved successfully";
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnString;
	}
}
