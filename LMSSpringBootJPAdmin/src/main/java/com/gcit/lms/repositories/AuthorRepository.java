package com.gcit.lms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gcit.lms.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
	@Query("from Author where authorName like %:searchString%")
	public List<Author> readAuthorsByName(@Param(value = "searchString") String authorName);
}
