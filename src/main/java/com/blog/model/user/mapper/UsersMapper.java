package com.blog.model.user.mapper;

import com.blog.model.user.entity.Users;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsersMapper {
    int addUser(Users users);

    Users findUserByUserName(String userName);

    Users findUserByUserId(Integer uerId);

    Users findUserByUserNameAndPassword(String userName, String password);
}