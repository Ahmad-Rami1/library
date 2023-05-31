package com.library.library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.library.library.expetions.AuthorNotFoundException;
import com.library.library.models.Author;
import com.library.library.repositories.AuthorRepository;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);

    }

    @Override
    public Optional<Author> getAuthorById(int id) {
        if (!authorRepository.existsById(id)) {
            throw new AuthorNotFoundException("Author not found");
        }
        return authorRepository.findById(id);
    }

    @Override
    public void deleteAuthor(int id) {
        if (authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
        } else {
            throw new AuthorNotFoundException("Author not found");
        }
    }

    @Override
    public Author updateAuthor(@PathVariable int id, @RequestBody Author author) {
        if (!authorRepository.existsById(id)) {
            throw new AuthorNotFoundException("Author not found");
        }
        Author authorToUpdate = authorRepository.findById(id).get();

        authorToUpdate.setName(author.getName());
        return authorToUpdate;

    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

}
