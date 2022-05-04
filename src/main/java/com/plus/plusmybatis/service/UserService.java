package com.plus.plusmybatis.service;

import com.plus.plusmybatis.mapper.MemberMapper;
import com.plus.plusmybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 * @Author: Madridista
 * @Date: 2022/5/4
 */
@Component
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Resource
    private MemberMapper memberMapper;

    public void test() {
        System.out.println(userMapper.getUserName());
        System.out.println(memberMapper.getMemberName());
    }
}
