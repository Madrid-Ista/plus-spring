package com.plus.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: Madridista
 * @Date: 2022/4/16
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);

//        System.out.println(applicationContext.getBean("userService"));
        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.test();
    }
}
