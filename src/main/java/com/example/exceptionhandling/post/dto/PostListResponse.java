package com.example.exceptionhandling.post.dto;

import com.example.exceptionhandling.post.entity.Post;

import lombok.Getter;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PostListResponse {

    private List<PostItem> posts;
    private int page;
    private int totalPages;
    private long totalElements;
    private boolean hasPrev;
    private boolean hasNext;
    private boolean isFirst;
    private boolean isLast;

    public PostListResponse(Page<Post> postPage) {
        this.posts = postPage.getContent().stream()
                .map(PostItem::new)
                .collect(Collectors.toList());
        this.page = postPage.getNumber();
        this.totalPages = postPage.getTotalPages();
        this.totalElements = postPage.getTotalElements();
        this.hasPrev = postPage.hasPrevious();
        this.hasNext = postPage.hasNext();
        this.isFirst = postPage.isFirst();
        this.isLast = postPage.isLast();
    }

    @Getter
    private static class PostItem {

        private Long postId;
        private String writer;
        private String title;

        private PostItem(Post post) {
            this.postId = post.getId();
            this.writer = post.getWriter();
            this.title = post.getTitle();
        }

    }

}
