package com.plus.plusmybatis.service;

import com.plus.plusmybatis.mybatisspring.PlusImportBeanDefinitionRegistrar;
import com.plus.plusmybatis.mybatisspring.PlusScan;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: Madridista
 * @Date: 2022/5/4
 */
@ComponentScan("com.plus.plusmybatis")
@Configuration
@PlusScan("com.plus.plusmybatis.mapper")
public class AppConfig {

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis.xml");
        return new SqlSessionFactoryBuilder().build(is);
    }
}
