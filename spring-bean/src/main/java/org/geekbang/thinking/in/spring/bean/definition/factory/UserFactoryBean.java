package org.geekbang.thinking.in.spring.bean.definition.factory;

import com.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * {@link User} 的 {@link org.springframework.beans.factory.FactoryBean} 实现
 *
 * @author wangweili
 */
public class UserFactoryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        return User.createUser();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
