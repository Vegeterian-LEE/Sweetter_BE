package com.sparta.clonesweetter.repository;

import com.sparta.clonesweetter.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
