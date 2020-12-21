package com.geekbang.thinking.in.spring.ioc.overview.dependency.lookup;

import com.geekbang.thinking.in.spring.ioc.overview.annotation.Super;
import com.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * 依赖查找案例 实时查找&延时查找
 * 1.通过名称的方式查找
 * 2.通过类型的方式查找
 * 3.通过名称+类型的方式联查
 * 4.通过类上的注解查找
 */
public class DependencyLookupDemo {
    public static void main(String[] args) {
        //创建BeanFactory上下文对象
        //配置XML配置文件
        //启动Spring应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-lookup-context.xml");
        //按照类型查找
        lookupByType(beanFactory);
        //按照符合类型查找
        lookupByCollectionType(beanFactory);
        //通过注解查找对象
        lookupByAnnotationType(beanFactory);
        //按照名称查找 实时查找
        lookupInRealTime(beanFactory);
        //延迟查找
        lookupInLazy(beanFactory);
    }

    @SuppressWarnings("all")
    private static void lookupByAnnotationType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = (Map) listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("查找到的标注了@Super的user对象:" + users);
        }
    }

    private static void lookupByCollectionType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            //将bean的名称作为key返回对象value集
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找到的所有user对象:" + users);
        }
    }

    private static void lookupByType(BeanFactory beanFactory) {
        //3.0后的操作
        User user = beanFactory.getBean(User.class);
        System.out.println("实时查找" + user);
    }

    @SuppressWarnings("all")
    private static void lookupInLazy(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        User user = objectFactory.getObject();
        System.out.println("实时查找" + user);
    }

    private static void lookupInRealTime(BeanFactory beanFactory) {
        User user = (User) beanFactory.getBean("user");
        System.out.println("延时查找" + user);
    }
}
