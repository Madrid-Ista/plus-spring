package com.plus.plusspring.service;

import com.plus.plusspring.spring.Autowired;
import com.plus.plusspring.spring.Component;

/**
 * @Author: Madridista
 * @Date: 2022/4/4
 */
@Component
public class UserService implements UserInterface {

    @Autowired
    private OrderService orderService;

    @Override
    public void test() {
        System.out.println(orderService);
    }
}
