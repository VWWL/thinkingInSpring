package org.geekbang.thinking.in.spring.bean.definition.factory;

import com.geekbang.thinking.in.spring.ioc.overview.domain.User;

/**
 * {@link User}工厂类
 */
public interface UserFactory {

    default User createUser() {
        return User.createUser();
    }
}
