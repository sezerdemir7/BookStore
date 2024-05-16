package org.example.bookstore.service;

import org.example.bookstore.dto.request.CreateBookRequest;
import org.example.bookstore.dto.response.BookResponse;
import org.example.bookstore.entity.Book;
import org.example.bookstore.exception.BookNotFoundException;
import org.example.bookstore.repository.BookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class BookServiceTest {

    private BookService bookService;
    private BookRepository bookRepository;
    @BeforeEach
    void setUp(){
        bookRepository= Mockito.mock(BookRepository.class);
        bookService=new BookService(bookRepository);
    }

    @Test
    void whenShouldReturnAllBook() {

        Book book1=new Book();
        book1.setId(1L);
        book1.setName("Test 1");
        book1.setAuthorName("Author 1");

        Book book2=new Book();
        book2.setId(2L);
        book2.setName("Test 2");
        book2.setAuthorName("Author 2");

        List<Book> bookList=new ArrayList<>();
        bookList.add(book1);
        bookList.add(book2);

        BookResponse response1=new BookResponse();
        response1.setId(1L);
        response1.setName("Test 1");
        response1.setAuthorName("Author 1");

        BookResponse response2=new BookResponse();
        response2.setId(2L);
        response2.setName("Test 2");
        response2.setAuthorName("Author 2");

        List<BookResponse> responseList=new ArrayList<>();
        responseList.add(response1);
        responseList.add(response2);


        Mockito.when(bookRepository.findAll()).thenReturn(bookList);
        List<BookResponse> result=bookService.getAllBook();

        Assertions.assertIterableEquals(result,responseList);
        Mockito.verify(bookRepository).findAll();

    }

    @Test
    void whenShouldReturnBook_ByBookId(){
        Book book=new Book();
        book.setId(1L);
        book.setName("Test 1");
        book.setAuthorName("Author 1");

        Mockito.when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        Book result=bookService.getBookByBookId(book.getId());

        Assertions.assertEquals(result,book);
        Mockito.verify(bookRepository).findById(book.getId());
    }


    @Test
    void whenShouldReturnBookResponse_ByBookId_Found(){
        Book book=new Book();
        book.setId(1L);
        book.setName("Test 1");
        book.setAuthorName("Author 1");
        book.setPrice(50);

        BookResponse response=new BookResponse();
        response.setAuthorName(book.getAuthorName());
        response.setName(book.getName());
        response.setId(book.getId());
        response.setPrice(book.getPrice());
        Mockito.when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        BookResponse result=bookService.getBookById(book.getId());

        Assertions.assertEquals(result,response);
        Mockito.verify(bookRepository).findById(book.getId());


    }
    @Test
    void whenShouldReturnBookResponse_ByBookId_NotFound(){
        Mockito.when(bookRepository.findById(1L)).thenReturn(Optional.empty());


        BookNotFoundException exception = assertThrows(BookNotFoundException.class, () -> {
            bookService.getBookByBookId(1L);
        });

        assertEquals("Not book found with this ID:1", exception.getMessage());
    }

    @Test
    void whenSaveBook_thenReturnBookResponse() {
        CreateBookRequest request=new CreateBookRequest();
        request.setName("test 1");
        request.setAuthorName("author 1");
        request.setPrice(50);

        Book book = new Book();
        book.setAuthorName(request.getAuthorName());
        book.setName(request.getName());
        book.setPrice(request.getPrice());

        Book savedBook = new Book();
        savedBook.setId(1L);
        savedBook.setAuthorName(request.getAuthorName());
        savedBook.setName(request.getName());
        savedBook.setPrice(request.getPrice());

        BookResponse bookResponse =new BookResponse();
        bookResponse.setId(savedBook.getId());
        bookResponse.setName(savedBook.getName());
        bookResponse.setAuthorName(savedBook.getAuthorName());
        bookResponse.setPrice(savedBook.getPrice());

        Mockito.when(bookRepository.save(any(Book.class))).thenReturn(savedBook);
        BookResponse result=bookService.saveBook(request);

        Assertions.assertEquals(result,bookResponse);

        Mockito.verify(bookRepository).save(any(Book.class));

    }



        @AfterEach
    void tearDown() {

    }
}