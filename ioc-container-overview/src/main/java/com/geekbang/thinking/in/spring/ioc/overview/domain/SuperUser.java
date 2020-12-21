package com.geekbang.thinking.in.spring.ioc.overview.domain;

import com.geekbang.thinking.in.spring.ioc.overview.annotation.Super;

/**
 * 超级用户
 */
@Super
public class SuperUser extends User {
    private String address;

    public SuperUser() {
        System.out.println("SuperUser");
    }

    @Override
    public String toString() {
        return "SuperUser{" +
                "address='" + address + '\'' +
                "} " + super.toString();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
