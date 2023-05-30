package com.library.library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.library.expetions.BookNotFoundException;
import com.library.library.models.Book;
import com.library.library.repositories.BookRepository;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> getBookById(int id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException("Book not found");
        }
        return bookRepository.findById(id);
    }

    @Override
    public void deleteBook(int id) throws BookNotFoundException {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException("Book not found");
        }
        bookRepository.deleteById(id);
    }

    @Override
    public Book updateBook(int id, Book book) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException("Book not found");
        }
        Book bookToUpdate = bookRepository.findById(id).get();
        bookToUpdate.setAuthor(book.getAuthor());
        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setPublischedYear(book.getPublischedYear());

        return bookRepository.save(bookToUpdate);
    }
}
