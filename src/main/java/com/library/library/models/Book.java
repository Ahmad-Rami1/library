package com.library.library.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotNull;


import java.util.Objects;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, name = "title")
    @NotBlank(message = "Title is mandatory")
    private String title;
    @Column(nullable = false, name = "author")

    @NotBlank(message = "Author is mandatory")
    private String author;
    @Column(nullable = false, name = "published_year")
    @NotNull(message = "Published year is mandatory")

    private int publischedYear;

    public Book() {
    }

    public Book(int id, String title, String author, int publischedYear) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publischedYear = publischedYear;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublischedYear() {
        return this.publischedYear;
    }

    public void setPublischedYear(int publischedYear) {
        this.publischedYear = publischedYear;
    }

    public Book id(int id) {
        setId(id);
        return this;
    }

    public Book title(String title) {
        setTitle(title);
        return this;
    }

    public Book author(String author) {
        setAuthor(author);
        return this;
    }

    public Book publischedYear(int publischedYear) {
        setPublischedYear(publischedYear);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Book)) {
            return false;
        }
        Book book = (Book) o;
        return id == book.id && Objects.equals(title, book.title) && Objects.equals(author, book.author)
                && publischedYear == book.publischedYear;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, publischedYear);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", title='" + getTitle() + "'" +
                ", author='" + getAuthor() + "'" +
                ", publischedYear='" + getPublischedYear() + "'" +
                "}";
    }

}
