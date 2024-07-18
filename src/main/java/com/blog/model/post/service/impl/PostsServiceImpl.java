package com.blog.model.post.service.impl;

import com.blog.model.post.entity.Posts;
import com.blog.model.post.mapper.PostsMapper;
import com.blog.model.post.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostsServiceImpl implements PostsService {
    @Autowired
    PostsMapper postsMapper;

    @Override
    public void addPost(Posts posts) {
        postsMapper.addPost(posts);
    }

    @Override
    public List<Posts> findAllPostByUserId(Integer userId) {
        return postsMapper.findAllPostByUserId(userId);
    }

    @Override
    public Posts findPostByPostId(Integer postId) {
        return postsMapper.findPostByPostId(postId);
    }

    @Override
    public void updatePostByPostId(String title, String content, Integer postId) {
        postsMapper.updatePostByPostId(title, content, postId);
    }

    @Override
    public void deletePostByPostId(Integer postId) {
        postsMapper.deletePostByPostId(postId);
    }
}
