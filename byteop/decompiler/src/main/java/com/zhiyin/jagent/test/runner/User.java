/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.zhiyin.jagent.test.runner.User
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package com.zhiyin.jagent.test.runner;

import java.io.PrintStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class User {
    private static final Logger log = LoggerFactory.getLogger(User.class);
    private String name;

    public boolean equals(Object object) {
        void o;
        System.out.println("Enter method-> com/zhiyin/jagent/test/runner/User.equals");
        if (o == this) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User other = (User)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        String this$name = this.getName();
        String other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) {
            return false;
        }
        return true;
    }

    public String toString() {
        System.out.println("Enter method-> com/zhiyin/jagent/test/runner/User.toString");
        return "User(name=" + this.getName() + ")";
    }

    public int hashCode() {
        System.out.println("Enter method-> com/zhiyin/jagent/test/runner/User.hashCode");
        int PRIME = 59;
        int result = 1;
        String $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        return result;
    }

    public String getName() {
        System.out.println("Enter method-> com/zhiyin/jagent/test/runner/User.getName");
        return this.name;
    }

    public void setName(String string) {
        void name;
        System.out.println("Enter method-> com/zhiyin/jagent/test/runner/User.setName");
        this.name = name;
    }

    protected boolean canEqual(Object object) {
        void other;
        System.out.println("Enter method-> com/zhiyin/jagent/test/runner/User.canEqual");
        return other instanceof User;
    }

    public void hello() {
        System.out.println("Enter method-> com/zhiyin/jagent/test/runner/User.hello");
        log.info("hello:" + this.name);
    }
}

