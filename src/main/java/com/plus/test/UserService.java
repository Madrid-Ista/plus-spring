package com.plus.test;


import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: Madridista
 * @Date: 2022/4/16
 */
@Component
public class UserService {

    @Resource
    private OrderService orderService;

    public void test() {
        System.out.println(orderService);
    }
}
