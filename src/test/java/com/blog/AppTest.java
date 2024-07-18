package com.blog;

import com.blog.common.utils.Md5EncryptUtil;
import com.blog.model.user.entity.Users;
import com.blog.model.user.mapper.UsersMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
public class AppTest {

    @Autowired
    UsersMapper usersMapper;

    @Test
    public void test1() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowDateTime = simpleDateFormat.format(new Date());
        String password = Md5EncryptUtil.generateMd5Str("123456");
        Users users = new Users(null, "张三", password, "100@163.com", nowDateTime, null);
        usersMapper.addUser(users);
    }
}
