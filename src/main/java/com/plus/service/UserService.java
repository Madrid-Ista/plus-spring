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
public class UserService implements BeanNameAware, InitializingBean {

    @Autowired
    private OrderService orderService;

    private String beanName;

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public void test() {
        System.out.println(orderService);
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("sadfsadfas");
    }
}
