package com.blog.model.post.service;

import com.blog.model.post.entity.Posts;
import com.blog.model.post.entity.PostsPageQuery;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface PostsService {
    void addPost(Posts posts);
    PageInfo<Posts> findAllPostByUserId(Integer userId, PostsPageQuery pageQuery);
    Posts findPostByPostId(Integer postId);
    void updatePostByPostId(String title, String content, Integer postId);
    void deletePostByPostId(Integer postId);
}
