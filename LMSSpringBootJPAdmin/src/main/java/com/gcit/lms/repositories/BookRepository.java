package com.gcit.lms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gcit.lms.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

}
