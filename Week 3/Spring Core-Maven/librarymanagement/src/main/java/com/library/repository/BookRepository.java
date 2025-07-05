    package com.library.repository;


    public class BookRepository {

        /**
         * Simulates saving a book record to a database.
         * @param bookTitle 
         */
        public void saveBook(String bookTitle) {
            System.out.println("BookRepository: Saving book '" + bookTitle + "' to the database.");
        }
    }
    