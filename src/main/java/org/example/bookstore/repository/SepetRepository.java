package org.example.bookstore.repository;

import org.example.bookstore.entity.Sepet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SepetRepository extends JpaRepository<Sepet,Long> {
   Optional<Sepet> findByUserId(Long id);
}
