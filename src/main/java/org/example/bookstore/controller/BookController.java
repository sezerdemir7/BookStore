package org.example.bookstore.controller;

import org.example.bookstore.dto.request.CreateBookRequest;
import org.example.bookstore.dto.response.BookResponse;
import org.example.bookstore.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Long id){
        return new ResponseEntity<>(bookService.getBookById(id), HttpStatus.FOUND);
    }
    @GetMapping("/allBook")
    public ResponseEntity<List<BookResponse>> getAllBook(){
        return new ResponseEntity<>(bookService.getAllBook(),HttpStatus.FOUND);
    }

    @PostMapping("/saveBook")
    public ResponseEntity<BookResponse> saveBook(@RequestBody CreateBookRequest request){
        return new ResponseEntity<>(bookService.saveBook(request),HttpStatus.CREATED);
    }


}
