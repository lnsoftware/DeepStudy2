package com.bfa.sbgl.app.api.security.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by wangqinghui on 2016/2/5.
 */
public class AuthUser {

    private Long id;

    private String name;

    private String login;

    private String password;


    private Set<AuthRole> roles = new HashSet<AuthRole>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<AuthRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<AuthRole> roles) {
        this.roles = roles;
    }
}
