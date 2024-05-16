package org.example.bookstore.service;

import org.example.bookstore.entity.Sepet;
import org.example.bookstore.repository.SepetRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class SepetServiceTest {


    private SepetRepository sepetRepository;
    private SepetService sepetService;

    @BeforeEach
    void setUp(){
        sepetRepository= Mockito.mock(SepetRepository.class);
        sepetService=new SepetService(sepetRepository);
    }

    @Test
    void testGetSepetByUser() {
        // Arrange
        Long userId = 1L;


        Sepet sepet = new Sepet();
        sepet.setId(1L);

        Mockito.when(sepetRepository.findByUserId(userId)).thenReturn(Optional.of(sepet));

        Sepet result = sepetService.getSepetByUser(userId);

        Assertions.assertEquals(sepet, result);
        Mockito.verify(sepetRepository).findByUserId(userId);
    }


}