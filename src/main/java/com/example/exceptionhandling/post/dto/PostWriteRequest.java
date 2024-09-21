package com.example.exceptionhandling.post.dto;

import jakarta.validation.constraints.NotBlank;

import lombok.Getter;

@Getter
public class PostWriteRequest {

    @NotBlank(message = "제목을 입력해 주세요.")
    private String title;

    @NotBlank(message = "작성자를 입력해 주세요.")
    private String writer;

    @NotBlank(message = "내용을 입력해 주세요.")
    private String content;

}
