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

import com.gcit.lms.entity.Branch;
import com.gcit.lms.repositories.BranchRepository;

@Controller
@RequestMapping("/lms/branches")
@ExposesResourceFor(Branch.class)
public class BranchController {

	private final BranchRepository repository;
	private final EntityLinks entityLinks;
	
	public BranchController(BranchRepository branchRepo, EntityLinks entityLinks) {
		this.repository = branchRepo;
		this.entityLinks = entityLinks;
	}	
	
	@GetMapping(produces = { "application/json", "application/xml" })
	HttpEntity<Resources<Branch>> getBranchs() {
		Resources<Branch> resources = new Resources<>(this.repository.findAll());
		resources.add(this.entityLinks.linkToCollectionResource(Branch.class));
		return new ResponseEntity<>(resources, HttpStatus.OK);
	}

	@GetMapping(path = "/{id}", produces = { "application/json", "application/xml" })
	HttpEntity<Resource<Branch>> getBranch(@PathVariable Integer id) {
		Resource<Branch> resource = new Resource<>(this.repository.getOne(id));
		resource.add(this.entityLinks.linkToSingleResource(Branch.class, id));
		return new ResponseEntity<>(resource, HttpStatus.OK);
	}
	
	@PostMapping(value = "/branch", produces = { "application/json", "application/xml" })
	HttpEntity<Resource<Branch>> postBranchs(@RequestBody final Branch body) {
		try {
			Branch branch = this.repository.save(body);
			Resource<Branch> resource = new Resource<>(branch);
			resource.add(this.entityLinks.linkToSingleResource(Branch.class, branch.getBranchId()));
			return new ResponseEntity<>(resource, HttpStatus.CREATED);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	@PutMapping(value = "/{id}", produces = { "application/json", "application/xml" })
	HttpEntity<Resource<Branch>> putBranch(@PathVariable Integer id, @RequestBody Branch body) {
		try {
			if (this.repository.existsById(id)) {
				body.setBranchId(id);
				Branch branch = this.repository.save(body);
				Resource<Branch> resource = new Resource<>(branch);
				resource.add(this.entityLinks.linkToSingleResource(Branch.class, branch.getBranchId()));
				return new ResponseEntity<>(resource, HttpStatus.OK);
			} else {
				body.setBranchId(id);
				Branch branch = this.repository.save(body);
				Resource<Branch> resource = new Resource<>(branch);
				resource.add(this.entityLinks.linkToSingleResource(Branch.class, branch.getBranchId()));
				return new ResponseEntity<>(resource, HttpStatus.CREATED);				
			}
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

	@DeleteMapping(path = "/{id}", produces = { "application/json", "application/xml" })
	HttpEntity<Void> deleteBranch(@PathVariable Integer id) {
		try {
			this.repository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}