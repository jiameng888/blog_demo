package com.blog.model.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * users
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Users {
    private Integer userId;

    private String username;

    private String password;

    private String email;

    private String created;

    private String lastModified;
}