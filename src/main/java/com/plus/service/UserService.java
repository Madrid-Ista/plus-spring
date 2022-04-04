package com.plus.service;

import com.plus.spring.Autowired;
import com.plus.spring.Component;

/**
 * @Author: Madridista
 * @Date: 2022/4/4
 */
@Component
public class UserService {

    @Autowired
    private OrderService orderService;

    public void test() {
        System.out.println(orderService);
    }

}
