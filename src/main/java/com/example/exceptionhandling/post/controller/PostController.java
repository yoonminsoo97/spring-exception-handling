package com.example.exceptionhandling.post.controller;

import com.example.exceptionhandling.post.dto.PostDetailResponse;
import com.example.exceptionhandling.post.dto.PostListResponse;
import com.example.exceptionhandling.post.dto.PostModifyRequest;
import com.example.exceptionhandling.post.dto.PostWriteRequest;
import com.example.exceptionhandling.post.service.PostService;

import jakarta.validation.Valid;

import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<Void> postWrite(@Valid @RequestBody PostWriteRequest postWriteRequest) {
        postService.postWrite(postWriteRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDetailResponse> postDetail(@Min(0) @PathVariable("postId") Long postId) {
        PostDetailResponse postDetailResponse = postService.postDetail(postId);
        return ResponseEntity.ok().body(postDetailResponse);
    }

    @GetMapping
    public ResponseEntity<PostListResponse> postList(@Min(0) @RequestParam("page") int page) {
        PostListResponse postListResponse = postService.postList(page);
        return ResponseEntity.ok().body(postListResponse);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Void> postModify(
            @PathVariable("postId") Long postId, @Valid @RequestBody PostModifyRequest postModifyRequest) {
        postService.postModify(postId, postModifyRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> postDelete(@Min(0) @PathVariable("postId") Long postId) {
        postService.postDelete(postId);
        return ResponseEntity.ok().build();
    }

}
