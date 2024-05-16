package org.example.bookstore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.bookstore.common.BaseEntity;

import java.util.List;

@Entity
@Getter
@Setter
public class Sepet extends BaseEntity {

    @OneToOne(fetch = FetchType.EAGER)
    private User user;

    @OneToMany(mappedBy = "sepet", cascade = CascadeType.REMOVE)
    private List<SepetOgesi> sepetOgesiList;

    private int totalPrice;
}
