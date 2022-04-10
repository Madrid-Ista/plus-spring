package com.plus.spring;

import java.beans.Introspector;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: Madridista
 * @Date: 2022/4/4
 */
public class PlusApplicationContext {

    private Class configClass;

    private static final String DEFAULT_SCOPE = "singleton";

    /**
     * bean对象定义map，线程安全
     */
    private ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    /**
     * 单例池，线程安全
     */
    private ConcurrentHashMap<String, Object> singletonObjects = new ConcurrentHashMap<>();

    /**
     * beanpostprocessor列表
     */
    private ArrayList<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();


    /**
     * 初始化plus-spring容器
     * 1.扫描配置类对象，获取包扫描路径
     * 2.获取包扫描路径下所有.class文件
     * 3.获取标有@Component注解的.class文件
     * 4.根据@Component注解和@Scope注解创建beanDefinition对象，并放入beanDefinitionMap中
     * 5.实例化单例bean（完成依赖注入）
     *
     * @param configClass 配置类对象
     */
    public PlusApplicationContext(Class configClass) {
        this.configClass = configClass;
        
        // 扫描-->BeanDefinition-->beanDefinitionMap
        if (configClass.isAnnotationPresent(ComponentScan.class)) {
            ComponentScan componentScanAnnotation = (ComponentScan) configClass.getAnnotation(ComponentScan.class);
            // 获取扫描路径
            String path = componentScanAnnotation.value();
            
            path = path.replace(".", "/");

            ClassLoader classLoader = PlusApplicationContext.class.getClassLoader();
            // 获取相对路径对应的资源
            URL resource = classLoader.getResource(path);

            File file = new File(resource.getFile());

            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (File f : files) {
                    String fileName = f.getAbsolutePath();

                    if (fileName.endsWith(".class")) {
                        // com\plus\service\UserService
                        String className = fileName.substring(fileName.indexOf("com"), fileName.indexOf(".class"));
                        // com.plus.service.UserService
                        className = className.replace("\\", ".");

                        try {
                            Class<?> clazz = classLoader.loadClass(className);

                            if (clazz.isAnnotationPresent(Component.class)) {

                                if (BeanPostProcessor.class.isAssignableFrom(clazz)) {
                                    BeanPostProcessor instance = (BeanPostProcessor) clazz.newInstance();
                                    beanPostProcessorList.add(instance);
                                }

                                // BeanDefinition
                                BeanDefinition beanDefinition = new BeanDefinition();
                                beanDefinition.setType(clazz);

                                if (clazz.isAnnotationPresent(Scope.class)) {
                                    beanDefinition.setScope(clazz.getAnnotation(Scope.class).value());
                                } else {
                                    beanDefinition.setScope(DEFAULT_SCOPE);
                                }

                                // 获取beanName
                                Component component = clazz.getAnnotation(Component.class);
                                String beanName = component.value();

                                if (beanName.equals("")) {
                                    beanName = Introspector.decapitalize(clazz.getSimpleName());
                                }

                                beanDefinitionMap.put(beanName, beanDefinition);

                            }
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        }


                    }
                }
            }
        }

        // 实例化单例bean
        for (String beanName : beanDefinitionMap.keySet()) {
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            if (beanDefinition.getScope().equals(DEFAULT_SCOPE)) {
                Object bean = createBean(beanName, beanDefinition);

                singletonObjects.put(beanName, bean);
            }
        }
    }

    private Object createBean(String beanName, BeanDefinition beanDefinition) {

        Class clazz = beanDefinition.getType();
        try {
            Object instance = clazz.getConstructor().newInstance();

            // 依赖注入
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    field.setAccessible(true);
                    field.set(instance, getBean(field.getName()));
                }
            }

            // Aware回调，回调时plus-spring会传入参数beanName
            if (instance instanceof BeanNameAware) {
                ((BeanNameAware)instance).setBeanName(beanName);
            }

            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                instance = beanPostProcessor.postProcessBeforeInitialization(beanName, instance);
            }

            // 初始化，初始化是plus-spring只负责调用接口定义的方法
            if (instance instanceof InitializingBean) {
                ((InitializingBean)instance).afterPropertiesSet();
            }

            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                instance = beanPostProcessor.postProcessAfterInitialization(beanName, instance);
            }


            return instance;

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Object getBean(String beanName) {

        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);

        if (Objects.isNull(beanDefinition)) {
            throw new NullPointerException();
        } else {
            String scope = beanDefinition.getScope();
            if (scope.equals(DEFAULT_SCOPE)) {
                // 单例
                Object bean = singletonObjects.get(beanName);
                if (Objects.isNull(bean)) {
                    bean = createBean(beanName, beanDefinition);
                    singletonObjects.put(beanName, bean);
                }
                return bean;
            } else {
                // 多例
                return createBean(beanName, beanDefinition);
            }
        }

    }
}
