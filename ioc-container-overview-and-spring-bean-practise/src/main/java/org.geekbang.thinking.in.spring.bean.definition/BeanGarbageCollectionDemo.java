package org.geekbang.thinking.in.spring.bean.definition;

import lombok.SneakyThrows;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanGarbageCollectionDemo {
    @SneakyThrows
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.register(BeanInitializationDemo.class);
        ac.refresh();
        ac.close();
        System.out.println("上下文已关闭");
        Thread.sleep(5000);
        System.gc();
        Thread.sleep(5000);
    }
}
