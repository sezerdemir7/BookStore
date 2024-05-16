package org.example.bookstore.exception;

public class InvalidUsernamePasswordException extends RuntimeException{
    public InvalidUsernamePasswordException(String message) {
        super(message);
    }
}
