package com.blog.model.post.entity;

import lombok.Data;

@Data
public class PostsPageQuery {
    private Integer pageNum;
    private Integer pageSize;
}
