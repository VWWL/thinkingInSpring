package org.geekbang.thinking.in.spring.bean.definition;

import lombok.SneakyThrows;
import org.geekbang.thinking.in.spring.bean.definition.factory.DefaultUserFactory;
import org.geekbang.thinking.in.spring.bean.definition.factory.UserFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.ServiceLoader;

public class SpecialBeanInstantiationDemo {
    @SneakyThrows
    @SuppressWarnings("all")
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("classpath:/META-INF/special-bean-instantiation-create.xml");
        displayServiceLoader();
        ServiceLoader<UserFactory> serviceLoader = ac.getBean("userFactoryServiceLoader", ServiceLoader.class);
        displayServiceLoader(serviceLoader);
        AutowireCapableBeanFactory beanFactory = ac.getAutowireCapableBeanFactory();
        DefaultUserFactory factory = beanFactory.createBean(DefaultUserFactory.class);
        System.out.println(factory.createUser());
        ac.close();
    }

    public static void displayServiceLoader() {
        ServiceLoader<UserFactory> userFactories = ServiceLoader.load(UserFactory.class, SpecialBeanInstantiationDemo.class.getClassLoader());
        displayServiceLoader(userFactories);
    }

    public static void displayServiceLoader(ServiceLoader<UserFactory> serviceLoader) {
        Iterator<UserFactory> iterator = serviceLoader.iterator();
        if (iterator.hasNext()) {
            UserFactory userFactory = iterator.next();
            System.out.println(userFactory.createUser());
        }
    }
}
