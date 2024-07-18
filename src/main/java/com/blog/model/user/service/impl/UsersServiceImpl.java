package com.blog.model.user.service.impl;

import com.blog.common.utils.Md5EncryptUtil;
import com.blog.model.user.entity.Users;
import com.blog.model.user.mapper.UsersMapper;
import com.blog.model.user.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    UsersMapper usersMapper;

    /**
     * 添加用户
     *
     * @param user
     */
    @Override
    public void addUser(Users user) {
        String password = Md5EncryptUtil.generateMd5Str(user.getPassword());
        user.setPassword(password);
        usersMapper.addUser(user);
    }

    /**
     * 通过用户名查找用户信息
     *
     * @param userName
     * @return
     */
    @Override
    public Users findUserByUserName(String userName) {
        return usersMapper.findUserByUserName(userName);
    }

    /**
     * 通过用户ID查找用户信息
     *
     * @param uerId
     * @return
     */
    @Override
    public Users findUserByUserId(Integer uerId) {
        return usersMapper.findUserByUserId(uerId);
    }

    /**
     * 查找用户通过用户名和密码
     *
     * @param userName
     * @param password
     * @return
     */
    @Override
    public Users findUserByUserNameAndPassword(String userName, String password) {
        String md5Str = Md5EncryptUtil.generateMd5Str(password);
        return usersMapper.findUserByUserNameAndPassword(userName, md5Str);
    }
}
