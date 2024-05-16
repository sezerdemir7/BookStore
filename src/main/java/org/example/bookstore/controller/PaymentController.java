package org.example.bookstore.controller;

import org.example.bookstore.dto.response.PaymentResponse;
import org.example.bookstore.entity.Payment;
import org.example.bookstore.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/save/{userId}")
    public ResponseEntity<PaymentResponse> savePayment(@PathVariable Long userId){
        return new ResponseEntity<>(paymentService.save(userId), HttpStatus.OK);
    }
}
