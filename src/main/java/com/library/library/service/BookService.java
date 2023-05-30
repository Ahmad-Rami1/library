package com.library.library.service;

import java.util.List;
import java.util.Optional;

import com.library.library.expetions.BookNotFoundException;
import com.library.library.models.Book;

public interface BookService {
    public Book saveBook(Book book);

    public List<Book> getAllBooks();

    public Optional<Book> getBookById(int Id);

    public void deleteBook(int Id) throws BookNotFoundException;

    public Book updateBook(int id, Book book);
}
