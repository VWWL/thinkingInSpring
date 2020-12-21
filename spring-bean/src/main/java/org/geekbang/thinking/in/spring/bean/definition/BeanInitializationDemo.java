package org.geekbang.thinking.in.spring.bean.definition;

import org.geekbang.thinking.in.spring.bean.definition.factory.DefaultUserFactory;
import org.geekbang.thinking.in.spring.bean.definition.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 初始化Demo
 */
@Configuration
public class BeanInitializationDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.register(BeanInitializationDemo.class);
        ac.refresh();
        UserFactory factory = ac.getBean(UserFactory.class);
        System.out.println(factory);
        System.out.println("应用上下文准备关闭...");
        ac.close();
        System.out.println("应用上下文已关闭...");
    }

    @Bean(initMethod = "initUserFactory",destroyMethod = "destoryUserFactory")
    public UserFactory userFactory() {
        return new DefaultUserFactory();
    }
}
