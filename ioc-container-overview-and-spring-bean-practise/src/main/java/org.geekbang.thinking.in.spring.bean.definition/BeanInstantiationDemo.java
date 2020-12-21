package org.geekbang.thinking.in.spring.bean.definition;

import com.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 工厂
 */
public class BeanInstantiationDemo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-instantiation-create.xml");
        User userByStaticMethod = ac.getBean("user-by-static-method", User.class);
        System.out.println(userByStaticMethod);
        User userByInstanceMethod = ac.getBean("user-by-instance-method", User.class);
        System.out.println(userByInstanceMethod);
        System.out.println(userByInstanceMethod == userByStaticMethod);
        User userByFactoryBean = ac.getBean("user-by-factory-bean", User.class);
        System.out.println(userByFactoryBean);
    }
}
