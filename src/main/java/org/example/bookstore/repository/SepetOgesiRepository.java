package org.example.bookstore.repository;

import org.example.bookstore.entity.SepetOgesi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SepetOgesiRepository extends JpaRepository<SepetOgesi, Long> {

    Optional<SepetOgesi> findByBookId(Long bookId);
    //Optional<SepetOgesi> findByUserIdAndBookId(Long userId,Long bookId);
    @Query("SELECT s FROM SepetOgesi s WHERE s.user.id = :userId AND s.book.id = :bookId")
    Optional<SepetOgesi> findByUserIdAndBookId(@Param("userId") Long userId, @Param("bookId") Long bookId);

    void deleteBySepetId(Long bookId);
}
