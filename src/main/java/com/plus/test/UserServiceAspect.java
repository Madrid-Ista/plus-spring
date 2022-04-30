package com.plus.test;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @Author: Madridista
 * @Date: 2022/4/16
 */
@Component
@Aspect
public class UserServiceAspect {

    @Before(value = "execution(* com.plus.test.UserService.*(..))")
    public void before() {
        System.out.println("before...");
    }
}
