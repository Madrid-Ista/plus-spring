package com.plus.test;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Author: Madridista
 * @Date: 2022/4/16
 */
@ComponentScan("com.plus.test")
//@Configuration
@EnableAspectJAutoProxy
public class Config {

    @Bean
    public UserService userService1() {
        return new UserService();
    }
}
