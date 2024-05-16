package org.example.bookstore.service;

import org.example.bookstore.dto.response.BookResponse;
import org.example.bookstore.dto.response.SepetResponse;
import org.example.bookstore.entity.Book;
import org.example.bookstore.entity.Sepet;
import org.example.bookstore.entity.SepetOgesi;
import org.example.bookstore.entity.User;
import org.example.bookstore.exception.SepetNotFoundException;
import org.example.bookstore.repository.SepetRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SepetService {
    private final SepetRepository sepetRepository;


    public SepetService(SepetRepository sepetRepository) {
        this.sepetRepository = sepetRepository;

    }

    public void createSepetByUserId(User user) {
        Sepet sepet = new Sepet();
        sepet.setUser(user);
        sepetRepository.save(sepet);
    }

    public SepetResponse getSepetByUserId(Long id) {
        Sepet sepet = sepetRepository.findByUserId(id).
                orElseThrow(() -> new SepetNotFoundException("Not sepet found with this UserID:" + id));

        return mapToResponse(sepet);
    }

    public Sepet getSepetByUser(Long id) {
        Sepet sepet = sepetRepository.findByUserId(id).
                orElseThrow(() -> new SepetNotFoundException("Not sepet found with this UserID:" + id));

        return sepet;
    }


    public SepetResponse mapToResponse(Sepet sepet) {

        SepetResponse response = new SepetResponse();
        List<SepetOgesi> sepetOgesiList = sepet.getSepetOgesiList();
        List<BookResponse> bookResponses = new ArrayList<>();

        for (SepetOgesi sepetOgesi : sepetOgesiList) {
            BookResponse bookResponse = new BookResponse(); // Yeni bir BookResponse oluştur
            bookResponse.setId(sepetOgesi.getBook().getId());
            bookResponse.setName(sepetOgesi.getBook().getName());
            bookResponse.setAuthorName(sepetOgesi.getBook().getAuthorName());
            bookResponse.setPrice(sepetOgesi.getBook().getPrice());
            bookResponse.setAdet(sepetOgesi.getCount());

            bookResponses.add(bookResponse);
        }

        response.setBookList(bookResponses);
        response.setTotalPrice(sepet.getTotalPrice());

        return response;
    }


    public void updateSepet(Sepet sepet) {
        sepetRepository.save(sepet);
    }

    public SepetResponse addBookByItem(Long userId, SepetOgesi item) {
        Sepet sepet = sepetRepository.findByUserId(userId).
                orElseThrow(() -> new SepetNotFoundException("Bu kullanıcının sepeti bulunamadı"));

        if (!sepet.getSepetOgesiList().contains(item)) {
            sepet.getSepetOgesiList().add(item);

        }
        sepet.setTotalPrice(sepet.getTotalPrice() + item.getTotalPrice());
        Sepet update = sepetRepository.save(sepet);
        return mapToResponse(update);
    }

    public SepetResponse deleteItem(Long userId, SepetOgesi item) {
        Sepet sepet = sepetRepository.findByUserId(userId).
                orElseThrow(() -> new SepetNotFoundException("Bu kullanıcının sepeti bulunamadı"));

        sepet.getSepetOgesiList().removeIf(itm -> itm.equals(item));
        sepet.setTotalPrice(sepet.getTotalPrice() - item.getTotalPrice() * item.getCount());

        Sepet update = sepetRepository.save(sepet);

        return mapToResponse(update);

    }
}
