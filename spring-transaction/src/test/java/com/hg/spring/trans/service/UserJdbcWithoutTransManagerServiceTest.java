package com.hg.spring.trans.service;

import com.hg.spring.trans.conf.H2DatabaseConfig;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by wangqinghui on 2016/3/1.
 */

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:test-rediscache.xml")
@ContextConfiguration(classes = {H2DatabaseConfig.class})
public class UserJdbcWithoutTransManagerServiceTest {

    @Resource
    UserJdbcWithoutTransManagerService  noTransService;

    @Resource
    JdbcTemplate jdbcTemplate;

    @Test
    public void testJdbcTemplate() throws Exception {

        BasicDataSource basicDataSource = (BasicDataSource)jdbcTemplate.getDataSource();
        // 如果为false,程序会抛出异常
        basicDataSource.setDefaultAutoCommit(false);
        //①.检查数据源autoCommit的设置
        System.out.println("autoCommit:"+ basicDataSource.getDefaultAutoCommit());

        //②.插入一条记录，初始分数为10
        jdbcTemplate.execute(
                "INSERT INTO spring_trans_user(id,name,score) VALUES(2,'tom',10)");

//        //③.调用工作在无事务环境下的服务类方法,将分数添加20分
//        service.addScore("tom",20);
//
//        //④.查看此时用户的分数
        int score = jdbcTemplate.queryForObject(
                "SELECT score FROM spring_trans_user WHERE name ='tom'",Integer.class);
        System.out.println("score:"+score);
        jdbcTemplate.execute("DELETE FROM spring_trans_user WHERE name='tom'");

    }

    @Test
    public void testAddScore() throws Exception {

        noTransService.addScore("tom",20);
    }
}