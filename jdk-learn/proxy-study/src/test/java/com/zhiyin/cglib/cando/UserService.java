package com.zhiyin.cglib.cando;

/**
 * Created by hg on 2017/6/26.
 */
public class UserService {

    public User selectById(Integer id) {
        User u = new User();
        u.setId(id);
        u.setName("hg-" + id);
        return u;
    }

    public void delById(Integer id) {

    }

    public void hello() {

    }
}
