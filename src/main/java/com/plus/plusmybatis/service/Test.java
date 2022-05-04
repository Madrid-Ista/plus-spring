package com.plus.plusmybatis.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: Madridista
 * @Date: 2022/5/4
 */
public class Test {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.test();

        //System.out.println(applicationContext.getBean("plusFactoryBean"));
        //System.out.println(applicationContext.getBean("&plusFactoryBean"));
    }

}
