package org.example.bookstore.service;

import jakarta.transaction.Transactional;
import org.example.bookstore.dto.response.PaymentResponse;
import org.example.bookstore.entity.Payment;
import org.example.bookstore.entity.Sepet;
import org.example.bookstore.entity.User;
import org.example.bookstore.repository.PaymentRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final UserService userService;
    private final SepetService sepetService;

    private final SepetOgesiService sepetOgesiService;

    public PaymentService(PaymentRepository paymentRepository, UserService userService, SepetService sepetService, SepetOgesiService sepetOgesiService) {
        this.paymentRepository = paymentRepository;
        this.userService = userService;
        this.sepetService = sepetService;
        this.sepetOgesiService = sepetOgesiService;
    }

    @Transactional
    public PaymentResponse save(Long userId) {
        Sepet sepet=sepetService.getSepetByUser(userId);
        User user=userService.getUserById(userId);

        Payment payment=new Payment();
        payment.setUser(user);
        payment.setTutar(sepet.getTotalPrice());
        Payment save=paymentRepository.save(payment);

        sepet.setTotalPrice(0);
        sepet.setSepetOgesiList(null);

        sepetOgesiService.deleteBySepetId(sepet.getId());

        sepetService.updateSepet(sepet);


        return mapToResponse(save);
    }

    private PaymentResponse mapToResponse(Payment payment){
        PaymentResponse paymentResponse=new PaymentResponse();
        paymentResponse.setTutar(payment.getTutar());
        paymentResponse.setUserId(payment.getUser().getId());
        return paymentResponse;
    }
}
