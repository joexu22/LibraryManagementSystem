package com.gcit.lms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gcit.lms.entity.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {

}
