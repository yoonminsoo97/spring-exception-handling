package com.example.exceptionhandling.post.dto;

import com.example.exceptionhandling.post.entity.Post;

import lombok.Getter;

@Getter
public class PostDetailResponse {

    private Long id;
    private String title;
    private String writer;
    private String content;

    public PostDetailResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.writer = post.getWriter();
        this.content = post.getContent();
    }

}
