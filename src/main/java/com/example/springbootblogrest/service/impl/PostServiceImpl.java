package com.example.springbootblogrest.service.impl;

import com.example.springbootblogrest.dto.PostDto;
import com.example.springbootblogrest.entity.Post;
import com.example.springbootblogrest.exception.ResourceNotFoundException;
import com.example.springbootblogrest.repository.PostRepository;
import com.example.springbootblogrest.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = mapToEntity(postDto);

        Post savedPost = postRepository.save(post);

        PostDto returnPost = mapToDto(new Post());

        return returnPost;
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts =postRepository.findAll();
        return posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return mapToDto(post);
    }

    @Override
    public PostDto updatePostById(Long id, PostDto postDto) {
        Post post = mapToEntity(getPostById(id));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());

        Post savedPost = postRepository.save(post);
        return mapToDto(savedPost);
    }

    @Override
    public void deletePostById(Long id) {
        mapToEntity(getPostById(id));
        postRepository.deleteById(id);
    }

    private PostDto mapToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());

        return postDto;
    }

    private Post mapToEntity(PostDto postDto) {
        Post post = new Post();
        post.setId(postDto.getId());
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        return post;
    }

}
