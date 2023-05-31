package com.library.library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.library.library.models.Author;

public interface AuthorService {
    public Author saveAuthor(Author author);

    public Optional<Author> getAuthorById(int id);

    public List<Author> getAllAuthors();

    public void deleteAuthor(int id);

    public Author updateAuthor(int id, Author author);

}
