package com.example.exceptionhandling.post.dto;

import jakarta.validation.constraints.NotBlank;

import lombok.Getter;

@Getter
public class PostModifyRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

}
