package org.geekbang.thinking.in.spring.bean.definition;

import org.geekbang.thinking.in.spring.bean.definition.factory.DefaultUserFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 单体bean注册实例
 */
public class SingletonBeanRegisterationDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        //创建UserFactory外部对象
        DefaultUserFactory userFactory = new DefaultUserFactory();
        ConfigurableListableBeanFactory beanFactory = ac.getBeanFactory();
        //注册外部单例
        beanFactory.registerSingleton("userFactory", userFactory);
        ac.refresh();
        DefaultUserFactory userFactory1 = ac.getBean("userFactory", DefaultUserFactory.class);
        System.out.println(userFactory1 == userFactory);
    }
}
