package com.gcit.lms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gcit.lms.entity.Borrower;

@Repository
public interface BorrowerRepository extends JpaRepository<Borrower, Integer> {

}
