package com.blog.model.post.entity;

import java.util.Date;
import lombok.Data;

/**
 * posts
 */
@Data
public class Posts{
    private Integer postId;

    private String title;

    private String content;

    private Integer userId;

    private Date created;

    private Date lastModified;
}