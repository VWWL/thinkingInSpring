package com.geekbang.thinking.in.spring.ioc.overview.container;

import com.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * IoC容器实例
 * 注解
 *
 * @author wangweili
 */
@Configuration
public class AnnotationApplicationContextAsIoCContainerDemo {
    public static void main(String[] args) {
        //注解配置的ApplicationContext
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //将当前类AnnotationApplicationContextAsIoCContainerDemo作为配置类
        applicationContext.register(AnnotationApplicationContextAsIoCContainerDemo.class);
        //启动应用上下文
        //这是spring容器必须调用的方法，重新刷新 用于启动
        //有synchronized 记录启动时间 初始化propertySources
        //Environment校验必须的properties
        //obtainFreshBeanFactory    抽象实现->创建DefaultListableBeanFactory 并读取其属性
        applicationContext.refresh();
        lookupByCollectionType(applicationContext);
        //停止
        //销毁所有bean 并关闭bean工厂
        applicationContext.close();
    }

    @Bean
    public User user() {
        User user = new User();
        user.setId(1L);
        user.setName("小马哥");
        return user;
    }

    private static void lookupByCollectionType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            //将bean的名称作为key返回对象value集
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找到的所有user对象:" + users);
        }
    }
}
