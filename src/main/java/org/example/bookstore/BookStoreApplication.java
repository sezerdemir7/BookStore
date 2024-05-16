package org.example.bookstore;

import org.example.bookstore.entity.Book;
import org.example.bookstore.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class BookStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookStoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(BookRepository bookRepository) {
        return args -> {
            // Kitapları oluştur
            Book book1 = new Book();
            book1.setName("Book 1");
            book1.setAuthorName("Author 1");
            book1.setPrice(50);

            Book book2 = new Book();
            book2.setName("Book 2");
            book2.setAuthorName("Author 2");
            book2.setPrice(60);

            Book book3 = new Book();
            book3.setName("Book 3");
            book3.setAuthorName("Author 3");
            book3.setPrice(60);

            Book book4 = new Book();
            book4.setName("Book 4");
            book4.setAuthorName("Author 4");
            book4.setPrice(60);


            bookRepository.saveAll(Arrays.asList(book1, book2,book3,book4));
        };
    }



}
