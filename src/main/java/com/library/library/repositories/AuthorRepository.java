package com.library.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.library.models.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

}
