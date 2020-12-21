package com.geekbang.thinking.in.spring.ioc.overview.dependency;

import com.geekbang.thinking.in.spring.ioc.overview.repository.UserRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class whoIsIocContainer {
    public static void main(String[] args) {
        //谁才是真正的IOC容器？？？ beanFactory vs ApplicationContext
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");
        //ApplicationContext是BeanFactory的超集
        UserRepository userRepository = (UserRepository) applicationContext.getBean("userRepository");
        //因为此时ApplicationContext就是beanFactory
        //BeanFactory接口提供了一些高级机制来管理一些对象，ApplicationContext是BeanFactory的子接口
        //简化了Aop的整合
        //提供了消息资源和国际化的处理
        //可以spring事件发布
        //提供web项目应用级别的上下文WebApplicationContext
        //总而言之，BeanFactory是framework配置的框架，和一些基本功能 ApplicationContext是BeanFactory的超集,提供了更多企业级功能
        System.out.println(userRepository.getBeanFactory() == applicationContext);//false
        //userRepository不等于beanFactory的原因：
        //  ConfigurableApplicationContext <- ApplicationContext <- BeanFactory

        //  ConfigurableApplicationContext#getBeanFactory() 原因：这是一个组合的BeanFactory  返回的是DefaultListableBeanFactory
        //上下文上，他们组合实现，又属于继承关系 所以为false

        //结论:ApplicationContext组合了beanFactory并强化了BeanFactory的功能
        //得到ApplicationContext时，需要调用其getBeanFactory方法才可以获取其真正的底层实现
    }
}
