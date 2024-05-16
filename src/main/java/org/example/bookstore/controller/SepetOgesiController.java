package org.example.bookstore.controller;

import org.example.bookstore.dto.request.AddBookRequest;
import org.example.bookstore.dto.request.DeleteBookRequest;
import org.example.bookstore.dto.response.SepetResponse;
import org.example.bookstore.service.SepetOgesiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/sepetogesi")
public class SepetOgesiController {

    private final SepetOgesiService sepetOgesiService;

    public SepetOgesiController(SepetOgesiService sepetOgesiService) {
        this.sepetOgesiService = sepetOgesiService;
    }

    @PutMapping("/addBook")
    public ResponseEntity<SepetResponse> addBook(@RequestBody AddBookRequest request) {
        return new ResponseEntity<>(sepetOgesiService.saveSepetOgesi(request), HttpStatus.OK);
    }

    @DeleteMapping("/deleteBook")
    public ResponseEntity<SepetResponse> deleteBook(@RequestBody DeleteBookRequest request) {
        return new ResponseEntity<>(sepetOgesiService.deleteByBookId(request), HttpStatus.OK);
    }
}
