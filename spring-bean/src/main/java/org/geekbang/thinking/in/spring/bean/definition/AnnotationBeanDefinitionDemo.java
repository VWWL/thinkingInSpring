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

import static org.springframework.beans.factory.support.BeanDefinitionBuilder.genericBeanDefinition;

/**
 * 注解的BeanDefinition实例
 */
//方式3 通过import导入
@Import(AnnotationBeanDefinitionDemo.Config.class)
public class AnnotationBeanDefinitionDemo {
    public static void main(String[] args) {
        @Cleanup AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        //方式1：register Configuration Class
        ac.registerBean(Config.class);
        //通过BeanDefinition注册API实现
        registerBeanDefinition(ac, "mercyblitz-user", User.class);
        registerBeanDefinition(ac, User.class);
        //启动上下文
        ac.refresh();
        //register三种方式不会重复注册
        Map<String, Config> beansOfType = ac.getBeansOfType(Config.class);
        System.out.println(beansOfType);
        Map<String, User> users = ac.getBeansOfType(User.class);
        System.out.println(users);
        //{annotationBeanDefinitionDemo.Config=org.geekbang.in.spring.bean.definition.AnnotationBeanDefinitionDemo$Config@150c158}
        //{mercyblitz-user=User{id=1, name='小马哥'},
        // com.itheima.thinking.in.spring.ioc.overview.domain.User#0=User{id=1, name='小马哥'},
        // user=User{id=1, name='小马哥'}}
    }

    /**
     * 命名Bean的注册方式
     *
     * @param registry  注册器
     * @param beanName  命名
     * @param beanClass beanClass
     */
    public static void registerBeanDefinition(BeanDefinitionRegistry registry, String beanName, Class<?> beanClass) {
        BeanDefinitionBuilder beanDefinitionBuilder = genericBeanDefinition(beanClass);
        beanDefinitionBuilder.addPropertyValue("id", 1L)
                .addPropertyValue("name", "小马哥");
        if (StringUtils.hasText(beanName)) {
            //注册 为该bean取名
            registry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
            return;
        }
        //非命名方式
        BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), registry);
    }

    /**
     * 非命名bean的注册方式
     *
     * @param registry  注册器
     * @param beanClass beanClass
     */
    public static void registerBeanDefinition(BeanDefinitionRegistry registry, Class<?> beanClass) {
        //判断如果beanName参数存在，存在则使用beanName，否则自动生成
        registerBeanDefinition(registry, null, beanClass);
    }

    //方式2 定义当前类作为spring bean组建
    @Component
    public static class Config {
        //方式1
        @Bean(name = {"user", "wangweili-user"})
        public User user() {
            User user = new User();
            user.setId(1L);
            user.setName("小马哥");
            return user;
        }
    }
}
