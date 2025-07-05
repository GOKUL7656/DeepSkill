    package com.library;

    import com.library.service.BookService; 
    import org.springframework.context.ApplicationContext;
    import org.springframework.context.support.ClassPathXmlApplicationContext;

    public class LibraryApp { 

        public static void main(String[] args) {
            System.out.println("--- Starting Library Management Application ---");

            ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
            System.out.println("Spring Application Context loaded successfully.");

            BookService bookService = context.getBean("bookService", BookService.class); 
            System.out.println("BookService bean retrieved from Spring context.");

            bookService.addBook("The Lord of the Rings");

            ((ClassPathXmlApplicationContext) context).close();
            System.out.println("--- Application Finished ---");
        }
    }
    