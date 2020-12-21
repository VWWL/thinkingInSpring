package org.geekbang.thinking.in.spring.bean.definition.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {
    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean#destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean#afterPropertiesSet");
    }

    @PostConstruct
    public void init() {
        System.out.println("PostConstruct#init");
    }

    @PreDestroy
    public void drop() {
        System.out.println("PreDestroy#drop");
    }

    public void myInit() {
        System.out.println("DefaultUserFactory#myInit");
    }

    public void myDestroy() {
        System.out.println("DefaultUserFactory#myDestroy");
    }

    @Override
    public void finalize() {
        System.out.println("当前Bean正在被回收");
    }
}
