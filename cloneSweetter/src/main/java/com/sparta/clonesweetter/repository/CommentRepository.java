package com.sparta.clonesweetter.repository;

import com.sparta.clonesweetter.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByProjectId(Long id);
}

