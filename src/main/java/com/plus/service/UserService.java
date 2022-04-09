package com.plus.service;

import com.plus.spring.Autowired;
import com.plus.spring.BeanNameAware;
import com.plus.spring.Component;
import com.plus.spring.InitializingBean;

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
