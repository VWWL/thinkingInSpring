package org.geekbang.thinking.in.spring.dependency.lookup;

import lombok.Cleanup;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 通过{@link org.springframework.beans.factory.ObjectProvider}进行依赖查找
 * 
 * @author wangweili
 */
public class ObjectProviderDemo {

    public static void main(String[] args) {
        @Cleanup AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //将该类注册为配置类
        applicationContext.register(ObjectProviderDemo.class);
        applicationContext.refresh();
        lookupByObjectProvider(applicationContext);
    }

    @Bean
    public String helloWorld() {
        return "Hello,World";
    }

    private static void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
        System.out.println(objectProvider.getObject());
    }

}