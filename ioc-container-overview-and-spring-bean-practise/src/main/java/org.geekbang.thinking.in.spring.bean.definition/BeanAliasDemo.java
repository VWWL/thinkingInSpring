package org.geekbang.thinking.in.spring.bean.definition;

import com.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanAliasDemo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-definition-context.xml");
        User myUser = ac.getBean("myUser", User.class);
        User user = ac.getBean("user", User.class);
        System.out.println(user == myUser);
    }
}
