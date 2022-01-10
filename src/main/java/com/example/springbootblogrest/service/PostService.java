package com.example.springbootblogrest.service;

import com.example.springbootblogrest.dto.PostDto;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);
    List<PostDto> getAllPosts();
    PostDto getPostById(Long id);
    PostDto updatePostById(Long id, PostDto postDto);
    void deletePostById(Long id);
}
