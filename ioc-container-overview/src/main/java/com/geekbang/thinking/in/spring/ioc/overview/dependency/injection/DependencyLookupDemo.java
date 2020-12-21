package com.geekbang.thinking.in.spring.ioc.overview.dependency.injection;

import com.geekbang.thinking.in.spring.ioc.overview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 依赖注入案例 实时注入&延时注入
 */
public class DependencyLookupDemo {
    public static void main(String[] args) {
        //创建BeanFactory上下文对象
        //配置XML配置文件
        //启动Spring应用上下文
        //依赖注入
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");
        UserRepository userRepository = (UserRepository) beanFactory.getBean("userRepository");
        System.out.println(userRepository.getUsers());
        //依赖注入
        System.out.println(userRepository.getBeanFactory());
        //defining beans [user,objectFactory,superUser,userRepository]; root of factory hierarchy
        System.out.println(userRepository.getBeanFactory() == beanFactory);
        //false
        //依赖查找
        //System.out.println(beanFactory.getBean(BeanFactory.class)); 报错，无法getbean
        //原因：注入的beanFactory是一个内建对象。依赖查找与依赖注入并不一样，并不同源

        //延时注入
        ObjectFactory<ApplicationContext> objectFactory = userRepository.getObjectFactory();
        System.out.println(objectFactory.getObject() == beanFactory);
        //true
        //原因：在autowire注入的时候，自动帮我们注入了一个ApplicationContext

        //容器内建bean
        Environment environment = beanFactory.getBean(Environment.class);
        System.out.println("获取environment类型的bean:" + environment);


        /**
         *   依赖注入的三种方式：
         *   1.内部容器构建的Bean ({@link BeanFactory}、{@link ClassPathXmlApplicationContext})
         *   2.自定义bean 类似于{@link UserRepository}
         *   3.容器内建依赖
         */

    }
}
