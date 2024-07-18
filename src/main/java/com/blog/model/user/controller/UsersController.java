package com.blog.model.user.controller;

import com.blog.common.apiresult.ApiResponse;
import com.blog.common.apiresult.AppExceptionCodeMsg;
import com.blog.common.utils.TokenUtil;
import com.blog.model.user.entity.Users;
import com.blog.model.user.service.UsersService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UsersController {

    @Autowired
    UsersService usersService;

    @PostMapping("/register")
    public ApiResponse userRegister(@RequestBody Users users) {
        Users user = usersService.findUserByUserName(users.getUsername());
        if (user == null) {
            usersService.addUser(users);
            return ApiResponse.success("ok");
        }
        return ApiResponse.error(AppExceptionCodeMsg.USERNAME_ALREADY_EXIST);
    }

    @PostMapping("/login")
    public ApiResponse userLogin(HttpSession session, String userName, String password) {
        Users user = usersService.findUserByUserNameAndPassword(userName, password);
        if (user == null) {
            return ApiResponse.error(AppExceptionCodeMsg.USER_NOT_EXIST);
        }
        String token = TokenUtil.generateToken(String.valueOf(user.getUserId()));
        session.setAttribute("loginUserToken", token);
        return ApiResponse.success("登录成功");
    }

    @GetMapping("/me")
    public ApiResponse getUserInfo(HttpSession session) {
        String loginUserToken = (String) session.getAttribute("loginUserToken");
        String subject = TokenUtil.getClaimsByToken(loginUserToken);
        int userId = Integer.parseInt(subject);
        Users user = usersService.findUserByUserId(userId);
        return ApiResponse.success(user);
    }
}
