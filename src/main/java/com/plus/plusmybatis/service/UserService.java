package com.plus.plusmybatis.service;

import com.plus.plusmybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @Author: Madridista
 * @Date: 2022/5/4
 */
@Component
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void test() {
        System.out.println(userMapper);
        System.out.println(userMapper.getUserName());
    }
}
