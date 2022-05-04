package com.plus.plusspring.service;

import com.plus.plusspring.spring.PlusApplicationContext;

/**
 * @Author: Madridista
 * @Date: 2022/4/4
 */
public class Test {

    public static void main(String[] args) {
        PlusApplicationContext applicationContext = new PlusApplicationContext(AppConfig.class);
        UserInterface userService = (UserInterface) applicationContext.getBean("userService");
        userService.test();
    }

}
