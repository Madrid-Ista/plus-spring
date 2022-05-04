package com.plus.plusmybatis.mybatisspring;

import com.plus.plusmybatis.mapper.UserMapper;
import com.plus.plusspring.spring.Component;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;
import java.util.Map;

/**
 * @Author: Madridista
 * @Date: 2022/5/4
 */
public class PlusImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {
        // 获取@PlusScan注解中的mapper路径
        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(PlusScan.class.getName());
        String path = (String) annotationAttributes.get("value");

        // 扫描mapper接口，registry是IOC容器
        PlusScanner plusScanner = new PlusScanner(registry);
        plusScanner.addIncludeFilter(new TypeFilter() {
            @Override
            public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
                return true;
            }
        });
        plusScanner.scan(path);
    }
}
