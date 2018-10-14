package com.gcit.lms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gcit.lms.entity.Branch;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Integer> {
}
