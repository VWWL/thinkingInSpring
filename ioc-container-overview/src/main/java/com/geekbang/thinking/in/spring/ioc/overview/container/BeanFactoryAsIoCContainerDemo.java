package com.geekbang.thinking.in.spring.ioc.overview.container;

import com.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * IoC容器实例
 *
 * @author wangweili
 */
public class BeanFactoryAsIoCContainerDemo {
    public static void main(String[] args) {
        //通常，我们都声明ApplicationContext,但有的时候也会运用到BeanFactory容器，该如何去运用？
        //创建BeanFactory容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //加载配置
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        //加载资源
        int i = reader.loadBeanDefinitions("classpath:/META-INF/dependency-lookup-context.xml");
        //i：读取到加载的bean的数量
        System.out.println(i);
        //DefaultListableBeanFactory是ListableBeanFactory实现 进行依赖查找
        lookupByCollectionType(beanFactory);
        //但是这样就没有一些复杂特性可以使用 例如事件或者是资源管理
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
