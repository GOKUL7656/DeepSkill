package com.library.service;

import com.library.repository.BookRepository;

public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addBook(String bookTitle) {
        System.out.println("BookService: Attempting to add book - '" + bookTitle + "'");
        bookRepository.saveBook(bookTitle);
        System.out.println("BookService: Book '" + bookTitle + "' added successfully.");
    }
}
