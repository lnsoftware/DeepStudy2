package com.hg.spring.trans.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service("noTransService")
public class UserServiceCustom {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void addScore(String userName,int toAdd){
        String sql = "UPDATE spring_trans_user u SET u.score = u.score + ? WHERE user_name =?";
        jdbcTemplate.update(sql,toAdd,userName);
    }


}