package com.plus.plusmybatis.mybatisspring;

import com.plus.plusmybatis.mapper.UserMapper;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: Madridista
 * @Date: 2022/5/4
 */
@Component
public class PlusFactoryBean implements FactoryBean {

    @Override
    public Object getObject() throws Exception {
        Object proxyInstance = Proxy.newProxyInstance(PlusFactoryBean.class.getClassLoader(), new Class[]{UserMapper.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(method.getName());
                return null;
            }
        });
        return proxyInstance;
    }

    @Override
    public Class<?> getObjectType() {
        return UserMapper.class;
    }
}
