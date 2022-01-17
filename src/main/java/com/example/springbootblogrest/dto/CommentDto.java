package com.example.springbootblogrest.dto;

import lombok.Data;

@Data
public class CommentDto {

    private Long id;
    private String name;
    private String email;
    private String message;
}
