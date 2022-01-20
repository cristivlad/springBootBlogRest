package com.example.springbootblogrest.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@ApiModel(value = "Comment model information")
@Data
public class CommentDto {

    @ApiModelProperty(value = "Blog comment id")
    private Long id;

    @ApiModelProperty(value = "Blog comment name")
    @NotEmpty(message = "Name should not be null or empty")
    private String name;

    @ApiModelProperty(value = "Blog comment email")
    @NotEmpty(message = "Email should not be null or empty")
    @Email(message = "Email si not valid")
    private String email;

    @ApiModelProperty(value = "Blog comment message")
    @NotEmpty
    @Size(min = 10, message = "Minimum message length should be 10 characters")
    private String message;
}
