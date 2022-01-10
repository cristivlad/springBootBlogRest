package com.example.springbootblogrest.controller;

import com.example.springbootblogrest.dto.PostDto;
import com.example.springbootblogrest.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), CREATED);
    }

    @GetMapping
    public List<PostDto> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id) {
        return new ResponseEntity<>(postService.getPostById(id), OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable(name="id") long id, @RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.updatePostById(id, postDto), OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id) {
        postService.deletePostById(id);
        return new ResponseEntity<>("Post deleted Successfully", OK);
    }
}
