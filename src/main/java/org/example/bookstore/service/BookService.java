package org.example.bookstore.service;

import org.example.bookstore.dto.request.CreateBookRequest;
import org.example.bookstore.dto.response.BookResponse;
import org.example.bookstore.entity.Book;
import org.example.bookstore.exception.BookNotFoundException;
import org.example.bookstore.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookResponse getBookById(Long id) {
      Book book=bookRepository.findById(id).orElseThrow(()-> new BookNotFoundException("Not book found with this ID:"+id));
      return mapToResponse(book);
    }

    public List<BookResponse> getAllBook() {
        List<Book> bookList=bookRepository.findAll();
        List<BookResponse> responseList=new ArrayList<>();

        for (Book book:bookList){
            responseList.add(mapToResponse(book));
        }
        return responseList;
    }

    public Book getBookByBookId(Long id){
        return bookRepository.findById(id).
                orElseThrow(()-> new BookNotFoundException("Not book found with this ID:"+id));
    }

    public BookResponse mapToResponse(Book book){
        BookResponse response=new BookResponse();
        response.setId(book.getId());
        response.setName(book.getName());
        response.setAuthorName(book.getAuthorName());
        response.setPrice(book.getPrice());

        return response;
    }

    public BookResponse saveBook(CreateBookRequest request) {
        Book book=new Book();
        book.setAuthorName(request.getAuthorName());
        book.setName(request.getName());
        book.setPrice(request.getPrice());
        Book save=bookRepository.save(book);
        return mapToResponse(save);
    }
}
