package com.example.exceptionhandling.post.service;

import com.example.exceptionhandling.post.dto.PostDetailResponse;
import com.example.exceptionhandling.post.dto.PostListResponse;
import com.example.exceptionhandling.post.dto.PostModifyRequest;
import com.example.exceptionhandling.post.dto.PostWriteRequest;
import com.example.exceptionhandling.post.entity.Post;
import com.example.exceptionhandling.post.exception.NotFoundPostException;
import com.example.exceptionhandling.post.repository.PostRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private static final int PAGE_SIZE = 10;
    private static final String PROPERTIES = "id";

    private final PostRepository postRepository;

    @Transactional
    public void postWrite(PostWriteRequest postWriteRequest) {
        Post post = Post.builder()
                .title(postWriteRequest.getTitle())
                .writer(postWriteRequest.getWriter())
                .content(postWriteRequest.getContent())
                .build();
        postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public PostDetailResponse postDetail(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(NotFoundPostException::new);
        return new PostDetailResponse(post);
    }

    @Transactional(readOnly = true)
    public PostListResponse postList(int page) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE, Sort.Direction.DESC, PROPERTIES);
        Page<Post> postPage = postRepository.findAll(pageable);
        return new PostListResponse(postPage);
    }


    @Transactional
    public void postModify(Long postId, PostModifyRequest postModifyRequest) {
        Post post = postRepository.findById(postId)
                .orElseThrow(NotFoundPostException::new);
        post.modify(postModifyRequest.getTitle(), postModifyRequest.getContent());
    }

    @Transactional
    public void postDelete(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(NotFoundPostException::new);
        postRepository.delete(post);
    }

}
