package com.example.springbootblogrest.controller;

import com.example.springbootblogrest.dto.PostDto;
import com.example.springbootblogrest.dto.PostResponse;
import com.example.springbootblogrest.entity.Post;
import com.example.springbootblogrest.service.PostService;
import com.example.springbootblogrest.utils.Constants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.springbootblogrest.utils.Constants.*;
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
    public PostResponse getAllPosts(@RequestParam(value = "pageNo", defaultValue = DEFAULT_PAGE_NO, required = false) int pageNo,
                                    @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE, required = false) int pageSize,
                                    @RequestParam(value = "sortBy", defaultValue = DEFAULT_SORT_BY, required = false) String sortBy,
                                    @RequestParam(value = "sortDir", defaultValue = DEFAULT_SORT_DIR, required = false) String sortDir){
        return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
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
