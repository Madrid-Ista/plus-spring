package com.plus.spring;

/**
 * @Author: Madridista
 * @Date: 2022/4/9
 */
public interface BeanPostProcessor {

    Object postProcessBeforeInitialization(String beanName, Object bean);

    Object postProcessAfterInitialization(String beanName, Object bean);
}
