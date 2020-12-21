package org.geekbang.thinking.in.spring.bean.definition;

import com.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanAliasDemo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-definition-context.xml");
        User user = (User) ac.getBean("xiaomage-user");
        User user1 = (User) ac.getBean("user");
        System.out.println(user == user1);
        //true
    }
}
