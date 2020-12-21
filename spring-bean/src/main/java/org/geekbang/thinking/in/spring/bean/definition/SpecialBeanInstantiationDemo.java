package org.geekbang.thinking.in.spring.bean.definition;

import org.geekbang.thinking.in.spring.bean.definition.factory.DefaultUserFactory;
import org.geekbang.thinking.in.spring.bean.definition.factory.UserFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.ServiceLoader;

import static java.util.ServiceLoader.load;

public class SpecialBeanInstantiationDemo {
    @SuppressWarnings("all")
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("classpath:/META-INF/special-bean-instantiation-create.xml");
        displayServiceLoader();
        ServiceLoader<UserFactory> serviceLoader = ac.getBean("userFactoryServiceLoader", ServiceLoader.class);
        displayServiceLoader(serviceLoader);
        //通过ApplicationContext获取AutowireCapableBeanFactory
        AutowireCapableBeanFactory beanFactory = ac.getAutowireCapableBeanFactory();
        //一定要用具体实例
        UserFactory factory = beanFactory.createBean(DefaultUserFactory.class);
        factory.createUser();
    }

    public static void displayServiceLoader() {
        ServiceLoader<UserFactory> userFactories = load(UserFactory.class, Thread.currentThread().getContextClassLoader());
        displayServiceLoader(userFactories);
    }

    private static void displayServiceLoader(ServiceLoader<UserFactory> serviceLoader) {
        Iterator<UserFactory> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            UserFactory userFactory = iterator.next();
            System.out.println(userFactory.createUser());
        }
    }
}
