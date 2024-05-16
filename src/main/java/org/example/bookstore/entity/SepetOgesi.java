package org.example.bookstore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.bookstore.common.BaseEntity;

@Entity
@Getter
@Setter
public class SepetOgesi extends BaseEntity {

    private int count;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sepet_id")
    private Sepet sepet; // Sepet ile ilişki ekledik

    private int totalPrice;
}
