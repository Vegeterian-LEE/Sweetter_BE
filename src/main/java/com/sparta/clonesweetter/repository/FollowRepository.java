package com.sparta.clonesweetter.repository;

import com.sparta.clonesweetter.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    Optional<Follow> findById(Long id);
    List<Follow> findAllById(Long userId);
    Optional<Follow> findByUserAndFollow(User user, Follow follow);
}
