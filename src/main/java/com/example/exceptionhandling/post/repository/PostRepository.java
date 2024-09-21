package com.example.exceptionhandling.post.repository;

import com.example.exceptionhandling.post.entity.Post;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
