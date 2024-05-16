package org.example.bookstore.exception;

public class SepetNotFoundException extends RuntimeException{
    public SepetNotFoundException(String message) {
        super(message);
    }
}
