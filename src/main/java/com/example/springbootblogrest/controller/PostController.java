package com.example.springbootblogrest.controller;

import com.example.springbootblogrest.dto.PostDto;
import com.example.springbootblogrest.dto.PostResponse;
import com.example.springbootblogrest.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.example.springbootblogrest.utils.Constants.*;
import static org.springframework.http.HttpStatus.*;

@Api(value = "CRUD rest apis for Post resource")
@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @ApiOperation(value = "Create post rest api")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), CREATED);
    }

    @ApiOperation(value = "Get all posts rest api")
    @GetMapping
    public PostResponse getAllPosts(@RequestParam(value = "pageNo", defaultValue = DEFAULT_PAGE_NO, required = false) int pageNo,
                                    @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE, required = false) int pageSize,
                                    @RequestParam(value = "sortBy", defaultValue = DEFAULT_SORT_BY, required = false) String sortBy,
                                    @RequestParam(value = "sortDir", defaultValue = DEFAULT_SORT_DIR, required = false) String sortDir){
        return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
    }

    @ApiOperation(value = "Get post for one id rest api")
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id) {
        return new ResponseEntity<>(postService.getPostById(id), OK);
    }

    @ApiOperation(value = "Update post rest api")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable(name="id") long id, @Valid @RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.updatePostById(id, postDto), OK);
    }

    @ApiOperation(value = "Delete post rest api")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id) {
        postService.deletePostById(id);
        return new ResponseEntity<>("Post deleted Successfully", OK);
    }
}
