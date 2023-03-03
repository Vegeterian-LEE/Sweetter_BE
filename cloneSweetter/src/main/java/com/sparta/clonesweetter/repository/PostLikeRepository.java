package com.sparta.clonesweetter.repository;

import com.sparta.clonesweetter.entity.PostLike;
import com.sparta.clonesweetter.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public class PostLikeRepository extends JpaRepository<PostLike, Long> {
    Optional<PostLike> findByPostAndUser(PostLike postLike, User user);
}
