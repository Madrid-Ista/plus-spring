package com.plus.plusspring.service;

import com.plus.plusspring.spring.BeanPostProcessor;
import com.plus.plusspring.spring.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: Madridista
 * @Date: 2022/4/9
 */
@Component
public class PlusBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(String beanName, Object bean) {
        if (beanName.equals("userService")) {
            System.out.println("2314514");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(String beanName, Object bean) {
        if (beanName.equals("userService")) {
            UserInterface proxyInstance = (UserInterface) Proxy.newProxyInstance(PlusBeanPostProcessor.class.getClassLoader(),
                    bean.getClass().getInterfaces(),
                    new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            System.out.println("切面逻辑");
                            return method.invoke(bean, args);
                        }
                    });
            return proxyInstance;
        }

        return bean;
    }
}
