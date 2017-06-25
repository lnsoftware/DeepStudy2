package com.zhiyin.mysql.acid;

import com.zhiyin.mysql.acid.domain.User;
import java.util.List;

/**
 * Created by hg on 2017/6/25.
 */
public interface IUserService {

    public void insert(User user);
    public List<User> selectAll();

    List<User> selectAll(int isoLevel);

    public void insertLong(User user);
    public void updateLong(User user);
}
