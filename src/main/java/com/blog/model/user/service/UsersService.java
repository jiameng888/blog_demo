package com.blog.model.user.service;


import com.blog.model.user.entity.Users;

public interface UsersService {
    void addUser(Users user);
    Users findUserByUserName(String userName);
    Users findUserByUserId(Integer uerId);
    Users findUserByUserNameAndPassword(String userName, String password);
}
