package org.example.bookstore.service;

import jakarta.transaction.Transactional;
import org.example.bookstore.dto.request.AddBookRequest;
import org.example.bookstore.dto.request.DeleteBookRequest;
import org.example.bookstore.dto.response.SepetResponse;
import org.example.bookstore.entity.Book;
import org.example.bookstore.entity.SepetOgesi;
import org.example.bookstore.entity.User;
import org.example.bookstore.exception.SepetNotFoundException;
import org.example.bookstore.repository.SepetOgesiRepository;
import org.springframework.stereotype.Service;

@Service
public class SepetOgesiService {

    private final SepetOgesiRepository sepetOgesiRepository;
    private final BookService bookService;
    private final UserService userService;

    private final SepetService sepetService;

    public SepetOgesiService(SepetOgesiRepository sepetOgesiRepository, BookService bookService, UserService userService, SepetService sepetService) {
        this.sepetOgesiRepository = sepetOgesiRepository;
        this.bookService = bookService;
        this.userService = userService;

        this.sepetService = sepetService;
    }

    public SepetResponse saveSepetOgesi(AddBookRequest request) {
        Book book = bookService.getBookByBookId(request.getBookId());
        User user =userService.getUserById(request.getUserId());
        int tPrice = book.getPrice();

        SepetOgesi item = sepetOgesiRepository.findByUserIdAndBookId(request.getUserId(), request.getBookId())
                .orElse(null);

        if (item != null) {
            item.setCount(item.getCount() + 1);
            item.setTotalPrice(tPrice);

        } else {
            item = new SepetOgesi();
            item.setCount(1);
            item.setBook(book);
            item.setTotalPrice(tPrice);
            item.setUser(user);
            item.setSepet(sepetService.getSepetByUser(user.getId()));
        }
        SepetOgesi save = sepetOgesiRepository.save(item);

        SepetResponse sepet = sepetService.addBookByItem(request.getUserId(), save);


        return sepet;

    }

    public SepetResponse deleteByBookId(DeleteBookRequest request) {
        SepetOgesi item = sepetOgesiRepository.findByUserIdAndBookId(request.getUserId(), request.getBookId()).orElseThrow(
                () -> new SepetNotFoundException("oge bulunamadi niye")
        );
        sepetOgesiRepository.delete(item);
       return sepetService.deleteItem(request.getUserId(), item);
    }

    @Transactional
    public void deleteBySepetId(Long sepetId){
        sepetOgesiRepository.deleteBySepetId(sepetId);
    }



}
