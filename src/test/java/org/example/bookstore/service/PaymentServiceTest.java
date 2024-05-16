package org.example.bookstore.service;

import org.example.bookstore.dto.response.PaymentResponse;
import org.example.bookstore.entity.Payment;
import org.example.bookstore.entity.Sepet;
import org.example.bookstore.entity.User;
import org.example.bookstore.repository.BookRepository;
import org.example.bookstore.repository.PaymentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class PaymentServiceTest {
    private  PaymentRepository paymentRepository;
    private  UserService userService;
    private  SepetService sepetService;
    private  SepetOgesiService sepetOgesiService;
    private PaymentService paymentService;

    @BeforeEach
    void setUp(){
        paymentRepository= Mockito.mock(PaymentRepository.class);
        userService=Mockito.mock(UserService.class);
        sepetService=Mockito.mock(SepetService.class);
        sepetOgesiService=Mockito.mock(SepetOgesiService.class);
        paymentService=new PaymentService(paymentRepository,userService,sepetService,sepetOgesiService);
    }


    @Test
    void whenSavePayment_thenReturnPaymentResponse() {
        // Arrange
        Long userId = 1L;

        User user = new User();
        user.setId(userId);
        user.setName("Test User");

        Sepet sepet = new Sepet();
        sepet.setId(1L);
        sepet.setUser(user);
        sepet.setTotalPrice(100);

        Payment payment = new Payment();
        payment.setId(1L);
        payment.setUser(user);
        payment.setTutar(100);

        Payment savedPayment = new Payment();
        savedPayment.setId(1L);
        savedPayment.setUser(user);
        savedPayment.setTutar(100);

        PaymentResponse response = new PaymentResponse();
        response.setUserId(userId);
        response.setTutar(savedPayment.getTutar());


        Mockito.when(sepetService.getSepetByUser(userId)).thenReturn(sepet);
        Mockito.when(userService.getUserById(userId)).thenReturn(user);
        Mockito.when(paymentRepository.save(any(Payment.class))).thenReturn(savedPayment);


        PaymentResponse result = paymentService.save(userId);

        Assertions.assertEquals(response, result);


        Mockito.verify(sepetService).getSepetByUser(userId);
        Mockito.verify(userService).getUserById(userId);
        Mockito.verify(sepetOgesiService).deleteBySepetId(sepet.getId());
        Mockito.verify(sepetService).updateSepet(sepet);
        Mockito.verify(paymentRepository).save(any(Payment.class));
    }


    @AfterEach
    void tearDown() {

    }

}