package com.blog.model.post.mapper;


import com.blog.model.post.entity.Posts;
import com.blog.model.post.entity.PostsPageQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostsMapper {
    int addPost(Posts posts);

    List<Posts> findAllPostByUserId(Integer userId, PostsPageQuery pageQuery);

    Posts findPostByPostId(Integer postId);

    int updatePostByPostId(String title, String content, Integer postId);

    int deletePostByPostId(Integer postId);
}