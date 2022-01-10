package com.example.springbootblogrest.service;

import com.example.springbootblogrest.dto.PostDto;
import com.example.springbootblogrest.dto.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);
    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
    PostDto getPostById(Long id);
    PostDto updatePostById(Long id, PostDto postDto);
    void deletePostById(Long id);
}
