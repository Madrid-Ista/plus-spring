package com.plus.plusmybatis.mybatisspring;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: Madridista
 * @Date: 2022/5/4
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(PlusImportBeanDefinitionRegistrar.class)
public @interface PlusScan {
    String value() default "";
}
