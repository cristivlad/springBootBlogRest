package com.example.springbootblogrest.repository;

import com.example.springbootblogrest.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
