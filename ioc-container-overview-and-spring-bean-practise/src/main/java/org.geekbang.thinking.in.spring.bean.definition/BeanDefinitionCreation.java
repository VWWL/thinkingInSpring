package org.geekbang.thinking.in.spring.bean.definition;

import com.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * {@link org.springframework.beans.factory.config.BeanDefinition} 构建实例
 */
public class BeanDefinitionCreation {
    public static void main(String[] args) {
        //1.通过BeanDefinitionBuilder创建
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        builder.addPropertyValue("name", "老王").addPropertyValue("id", 1L);
        builder.getBeanDefinition();
        //2.通过AbstractBeanDefinition及其派生类
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(User.class);
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.add("name", "老王").add("id", 1L);
        beanDefinition.setPropertyValues(propertyValues);
    }
}
