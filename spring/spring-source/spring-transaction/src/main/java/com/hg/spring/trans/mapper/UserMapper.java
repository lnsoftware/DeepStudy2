package com.hg.spring.trans.mapper;

import com.hg.spring.trans.entity.User;

import java.util.List;

public interface UserMapper {


    public void insert(User user);

    public User findUserById (int userId);

    public List<User> findAllUsers();

    /**
     * @return all the users
     */
    public List<User> getAllUsers();
    /**
     * @param user
     * @return the number of rows affected
     */
    public int insertUser(User user);
    /**
     * @param user
     * @return the number of rows affected
     */
    public int updateUser(User user);
}