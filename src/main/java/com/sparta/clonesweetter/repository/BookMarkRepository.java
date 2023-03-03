package com.sparta.clonesweetter.repository;

import com.sparta.clonesweetter.entity.BookMark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public interface BookMarkRepository extends JpaRepository<BookMark, Long> {
    Optional<BookMark> findById(Long id);
    Optional<BookMark> findByUserAndBookMark(User user, BookMark bookMark);
}
