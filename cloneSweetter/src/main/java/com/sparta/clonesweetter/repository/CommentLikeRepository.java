package com.sparta.clonesweetter.repository;

import com.sparta.clonesweetter.entity.Comment;
import com.sparta.clonesweetter.entity.CommentLike;
import com.sparta.clonesweetter.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    Optional<CommentLike> findByCommentAndUser(Comment comment, User user);
}
