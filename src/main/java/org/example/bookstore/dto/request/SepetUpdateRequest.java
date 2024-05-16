package org.example.bookstore.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.bookstore.entity.Book;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SepetUpdateRequest {


    private Long userId;
    private Long bookId;
}
