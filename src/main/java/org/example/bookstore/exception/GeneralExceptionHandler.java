package org.example.bookstore.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {

        Map<String,String> erors=new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error->{
            String fieldName=((FieldError) error).getField();
            String errorMessage= error.getDefaultMessage();;
            erors.put(fieldName,errorMessage);
        });

        return new ResponseEntity<>(erors, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<?> bookNotFoundException(BookNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(), NOT_FOUND);
    }

    @ExceptionHandler(SepetNotFoundException.class)
    public ResponseEntity<?> sepetNotFoundException(SepetNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(), NOT_FOUND);
    }
    @ExceptionHandler(InvalidUsernameException.class)
    public ResponseEntity<?> invalidUsernameException (InvalidUsernameException exception){
        return new ResponseEntity<>(exception.getMessage(), NOT_FOUND);
    }


    @ExceptionHandler(InvalidUsernamePasswordException.class)
    public ResponseEntity<?> invalidUsernamePasswordException (InvalidUsernamePasswordException exception){
        return new ResponseEntity<>(exception.getMessage(), NOT_FOUND);
    }


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> userNotFoundException (UserNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(), NOT_FOUND);
    }

}
