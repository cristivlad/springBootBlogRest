package com.example.springbootblogrest.repository;

import com.example.springbootblogrest.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// there is no need to anotate the interface with the @Repository
// because the SimpleJpaRepository implements the JpaRepository and it has the @Repository annotation
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPostId(long postId);
}
