package com.github.ericdahl.spring_boot_github_oauth.entity;

/**
 * Created by wangqinghui on 2016/2/18.
 */
public class OauthUser {

    private long id;
    private String name;
    private String email;
    private String avatar;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
