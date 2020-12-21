package org.geekbang.thinking.in.spring.bean.definition;

import com.geekbang.thinking.in.spring.ioc.overview.domain.User;
import lombok.Cleanup;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * {@link org.springframework.beans.factory.config.BeanDefinition} 的三种实现方式
 */
@Import(AnnotationBeanDefinitionDemo.Config.class)
public class AnnotationBeanDefinitionDemo {
    public static void main(String[] args) {
        @Cleanup AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //方式一 register注入
        context.register(Config.class);
        //方式二 BeanDefinition有名注入
        registerBeanDefinition(context, "myNewUser", User.class);
        //方式三 BeanDefinition无名注入
        registerBeanDefinition(context, User.class);
        context.refresh();
        Map<String, Config> configMap = context.getBeansOfType(Config.class);
        System.out.println(configMap);
        Map<String, User> userMap = context.getBeansOfType(User.class);
        System.out.println(userMap);
    }

    public static void registerBeanDefinition(BeanDefinitionRegistry registry, String beanName, Class<?> beanClass) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(beanClass);
        builder.addPropertyValue("name", "王唯力").addPropertyValue("id", 1L);
        if (StringUtils.hasText(beanName)) {
            registry.registerBeanDefinition(beanName, builder.getBeanDefinition());
            return;
        }
        BeanDefinitionReaderUtils.registerWithGeneratedName(builder.getBeanDefinition(), registry);
    }

    public static void registerBeanDefinition(BeanDefinitionRegistry registry, Class<?> beanClass) {
        registerBeanDefinition(registry, null, beanClass);
    }

    @Component
    public static class Config {
        @Bean
        public User user() {
            User user = new User();
            user.setName("小马哥");
            user.setId(1L);
            return user;
        }
    }
}
