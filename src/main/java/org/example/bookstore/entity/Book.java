package org.example.bookstore.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.example.bookstore.common.BaseEntity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "books")
@Getter
@Setter
public class Book extends BaseEntity {

    private String name;
    private String authorName;
    private int price;

    @OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE) // CascadeType.REMOVE ekledik
    private List<SepetOgesi> sepetOgesiList;
}
