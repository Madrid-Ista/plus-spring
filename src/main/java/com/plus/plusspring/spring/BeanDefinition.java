package com.plus.plusspring.spring;

/**
 * Bean 定义
 *
 * @Author: Madridista
 * @Date: 2022/4/4
 */
public class BeanDefinition {

    private Class type;
    private String scope;

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
