package com.library.library.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.library.models.Author;
import com.library.library.responses.MessageResponse;
import com.library.library.service.AuthorService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @PostMapping("/author")
    public ResponseEntity<MessageResponse> saveAuthor(@Valid @RequestBody Author author, BindingResult result) {
        // authorService.saveAuthor(author);
        if (result.hasErrors()) {
            String errorMessage = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            MessageResponse response = new MessageResponse(errorMessage);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        authorService.saveAuthor(author);
        MessageResponse message = new MessageResponse("Author was added");
        return ResponseEntity.ok(message);
    }

    @GetMapping("author/{id}")
    public ResponseEntity<Author> getAuthor(@PathVariable int id) {
        return ResponseEntity.ok(authorService.getAuthorById(id).get());
    }

    @GetMapping("author/index")
    public ResponseEntity<List<Author>> getAllAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @PutMapping(value = "author/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable int id, @RequestBody Author author) {
        Author toUpdateAuthor = authorService.getAuthorById(id).get();
        toUpdateAuthor.setName(author.getName());

        // MessageResponse message = new MessageResponse("Author updated");
        return ResponseEntity.ok(authorService.saveAuthor(toUpdateAuthor));

    }

    @DeleteMapping("author/{id}")
    public ResponseEntity<MessageResponse> deleteAuthor(@PathVariable int id) {
        authorService.deleteAuthor(id);
        MessageResponse message = new MessageResponse("Author deleted");
        return ResponseEntity.ok(message);
    }

}
