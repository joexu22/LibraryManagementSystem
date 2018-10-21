package com.gcit.lms.controller;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcit.lms.entity.Author;
import com.gcit.lms.repositories.AuthorRepository;

@Controller
@RequestMapping("/lms/authors")
@ExposesResourceFor(Author.class)
public class AuthorController {

	private final AuthorRepository repository;
	private final EntityLinks entityLinks;
	
	public AuthorController(AuthorRepository authorRepo, EntityLinks entityLinks) {
		this.repository = authorRepo;
		this.entityLinks = entityLinks;
	}	
	
	@GetMapping(produces = { "application/json", "application/xml" })
	HttpEntity<Resources<Author>> getAuthors() {
		try {
			Resources<Author> resources = new Resources<>(this.repository.findAll());
			resources.add(this.entityLinks.linkToCollectionResource(Author.class));
			return new ResponseEntity<>(resources, HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

	@GetMapping(path = "/{id}", produces = { "application/json", "application/xml" })
	HttpEntity<Resource<Author>> getAuthor(@PathVariable Integer id) {
		try {
			if (this.repository.existsById(id)) {
				Resource<Author> resource = new Resource<>(this.repository.getOne(id));
				resource.add(this.entityLinks.linkToSingleResource(Author.class, id));
				return new ResponseEntity<>(resource, HttpStatus.OK);
			} else {				
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PostMapping(value = "/author", produces = { "application/json", "application/xml" })
	HttpEntity<Resource<Author>> postAuthors(@RequestBody final Author body) {
		try {
			Author author = this.repository.save(body);
			Resource<Author> resource = new Resource<>(author);
			resource.add(this.entityLinks.linkToSingleResource(Author.class, author.getAuthorId()));
			return new ResponseEntity<>(resource, HttpStatus.CREATED);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	@PutMapping(value = "/{id}", produces = { "application/json", "application/xml" })
	HttpEntity<Resource<Author>> putAuthor(@PathVariable Integer id, @RequestBody Author body) {
		try {
			if (this.repository.existsById(id)) {
				body.setAuthorId(id);
				Author author = this.repository.save(body);
				Resource<Author> resource = new Resource<>(author);
				resource.add(this.entityLinks.linkToSingleResource(Author.class, author.getAuthorId()));
				return new ResponseEntity<>(resource, HttpStatus.OK);
			} else {
				body.setAuthorId(id);
				Author author = this.repository.save(body);
				Resource<Author> resource = new Resource<>(author);
				resource.add(this.entityLinks.linkToSingleResource(Author.class, author.getAuthorId()));
				return new ResponseEntity<>(resource, HttpStatus.CREATED);				
			}
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

	@DeleteMapping(path = "/{id}", produces = { "application/json", "application/xml" })
	HttpEntity<Void> deleteAuthor(@PathVariable Integer id) {
		try {
			this.repository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}