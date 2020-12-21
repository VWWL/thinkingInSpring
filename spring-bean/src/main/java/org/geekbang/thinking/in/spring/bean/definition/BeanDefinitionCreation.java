package org.geekbang.thinking.in.spring.bean.definition;

import com.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * {@link org.springframework.beans.factory.config.BeanDefinition} 构建实例
 */
public class BeanDefinitionCreation {
    public static void main(String[] args) {
        //1.通过BeanDefinitionBuilder构建
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        builder.addPropertyValue("id", 1);
        builder.addPropertyValue("name", "小马哥");
        //获取beanDefinition实例
        AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
        //beanDefinition并非bean的终态，可以自定义修改

        //2.通过AbstractBeanDefinition 以及其派生类
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(User.class);
        //通过MutablePropertyValues批量操作
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.add("id", 1).
                add("name", "小马哥");
        genericBeanDefinition.setPropertyValues(propertyValues);
    }
}
