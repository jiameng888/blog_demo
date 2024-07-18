package com.blog.model.post.service;

import com.blog.model.post.entity.Posts;

import java.util.List;

public interface PostsService {
    void addPost(Posts posts);
    List<Posts> findAllPostByUserId(Integer userId);
    Posts findPostByPostId(Integer postId);
    void updatePostByPostId(String title, String content, Integer postId);
    void deletePostByPostId(Integer postId);
}
