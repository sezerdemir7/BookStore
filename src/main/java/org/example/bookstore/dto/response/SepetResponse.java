package org.example.bookstore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.bookstore.entity.Book;
import org.example.bookstore.entity.User;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SepetResponse {

    private List<BookResponse> bookList;
    private int totalPrice;
}
