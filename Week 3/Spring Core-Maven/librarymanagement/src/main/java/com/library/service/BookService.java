    package com.library.service;

    import com.library.repository.BookRepository; // Import is BookRepository

    /**
     * BookService class handles business logic related to books.
     * It depends on BookRepository for data persistence.
     */
    public class BookService { // Class name is BookService

        // Dependency: BookRepository instance
        private BookRepository bookRepository; // Field type is BookRepository

        /**
         * Setter method for injecting BookRepository.
         * Spring will use this method to inject the 'bookRepository' bean.
         * @param bookRepository The BookRepository instance to be injected.
         */
        public void setBookRepository(BookRepository bookRepository) { // Parameter type is BookRepository
            this.bookRepository = bookRepository;
        }

        /**
         * Adds a new book by delegating the save operation to the repository.
         * @param bookTitle The title of the book to add.
         */
        public void addBook(String bookTitle) {
            System.out.println("BookService: Attempting to add book - '" + bookTitle + "'");
            // Delegate the actual saving to the BookRepository
            bookRepository.saveBook(bookTitle);
            System.out.println("BookService: Book '" + bookTitle + "' added successfully.");
        }
    }
    