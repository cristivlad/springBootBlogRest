package com.example.springbootblogrest.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CommentDto {

    private Long id;

    @NotEmpty(message = "Name should not be null or empty")
    private String name;

    @NotEmpty(message = "Email should not be null or empty")
    @Email(message = "Email si not valid")
    private String email;

    @NotEmpty
    @Size(min = 10, message = "Minimum message length should be 10 characters")
    private String message;
}
