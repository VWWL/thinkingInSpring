package org.geekbang.thinking.in.spring.bean.definition;

import lombok.SneakyThrows;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 垃圾回收(GC实例
 */
public class BeanGarbageCollectionDemo {
    @SneakyThrows
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.register(BeanInitializationDemo.class);
        ac.refresh();
        ac.close();
        System.out.println("应用上下文已关闭");
        Thread.sleep(5000);
        //强制触发gc
        System.gc();
        //当前DefaultUserFactory正在被回收
        //Bean在spring中可以被回收
        Thread.sleep(5000);
    }
}
