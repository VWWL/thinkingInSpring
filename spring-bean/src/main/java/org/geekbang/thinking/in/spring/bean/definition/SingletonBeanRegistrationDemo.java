package org.geekbang.thinking.in.spring.bean.definition;

import lombok.Cleanup;
import org.geekbang.thinking.in.spring.bean.definition.factory.DefaultUserFactory;
import org.geekbang.thinking.in.spring.bean.definition.factory.UserFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 单体bean注册实例
 */
public class SingletonBeanRegistrationDemo {
    public static void main(String[] args) {
        @Cleanup AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        //创建UserFactory外部对象
        UserFactory userFactory = new DefaultUserFactory();
        ConfigurableListableBeanFactory beanFactory = ac.getBeanFactory();
        //注册外部单例对象
        beanFactory.registerSingleton("userFactory",userFactory);
        ac.refresh();
        //通过依赖查找的方式来获取UserFactory
        UserFactory userFactoryByLookup = beanFactory.getBean("userFactory",UserFactory.class);
        System.out.println("userFactoryByLookup == userFactory : " + (userFactoryByLookup == userFactory));
        //相等，意味着外部对象被注册且可以被get
    }
}