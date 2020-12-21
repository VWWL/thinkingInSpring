package org.geekbang.thinking.in.spring.bean.definition.factory;

import lombok.SneakyThrows;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean#afterPropertiesSet");
    }

    @PostConstruct
    public void init() {
        System.out.println("UserFactory 初始化中...");
    }

    public void initUserFactory() {
        System.out.println("initUserFactory...");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("PreDestroy Bean销毁中");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean#Destroy");
    }

    public void destoryUserFactory() {
        System.out.println("destoryUserFactory...");
    }

    @Override
    @SneakyThrows
    public void finalize() {
        System.out.println("当前DefaultUserFactory正在被回收");
    }
}
