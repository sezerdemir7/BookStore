package org.example.bookstore.controller;

import org.example.bookstore.dto.response.SepetResponse;
import org.example.bookstore.service.SepetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/sepet")
public class SepetController {

    private final SepetService sepetService;

    public SepetController(SepetService sepetService) {
        this.sepetService = sepetService;
    }

    @GetMapping("/getByUserId/{userId}")
    public ResponseEntity<SepetResponse> getSepetByUserId(@PathVariable Long userId){

        return new ResponseEntity<>(sepetService.getSepetByUserId(userId), HttpStatus.FOUND);
    }



}
