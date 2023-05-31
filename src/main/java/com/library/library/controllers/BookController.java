package com.library.library.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.library.expetions.AuthorNotFoundException;
import com.library.library.models.Author;
import com.library.library.models.Book;
import com.library.library.responses.MessageResponse;
import com.library.library.service.AuthorService;
import com.library.library.service.BookService;

import jakarta.validation.Valid;

@RestController
// @RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorService authorService;

    @PostMapping("/book")
    public ResponseEntity<MessageResponse> saveBook(@Valid @RequestBody Book book, @RequestParam int authorId,
            BindingResult result) {

        Optional<Author> author = authorService.getAuthorById(authorId);

        if (!author.isPresent()) {
            throw new AuthorNotFoundException("Author not found");
        }

        book.setAuthor(author.get());

        if (result.hasErrors()) {
            // Here you could prepare a list of all error messages and send them in your
            // response
            String errorMessage = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            MessageResponse response = new MessageResponse(errorMessage);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        bookService.saveBook(book);
        MessageResponse response = new MessageResponse("Book was added");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/book/index")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        return ResponseEntity.ok(bookService.getBookById(id).get());
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<MessageResponse> deleteBook(@PathVariable int id) {
        // try {

        bookService.deleteBook(id);
        MessageResponse message = new MessageResponse("Book deleted successfully");
        return ResponseEntity.ok(message);
    }
    // } catch (BookNotFoundException e) {
    // // MessageResponse message = new MessageResponse("Book not found");
    // MessageResponse message = new MessageResponse("Book Not Found");
    // return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    // }
    // }

    @PutMapping("/book/{id}")
    public ResponseEntity<Object> updateBook(@PathVariable int id, @RequestBody Book book) {
        bookService.updateBook(id, book);
        MessageResponse message = new MessageResponse("Book updated successfully");
        return ResponseEntity.ok(message);
    }
}
