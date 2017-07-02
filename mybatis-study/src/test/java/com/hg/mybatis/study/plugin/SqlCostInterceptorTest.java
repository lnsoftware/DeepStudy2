package com.hg.mybatis.study.plugin;

import com.hg.mybatis.study.dao.UserDao;
import com.hg.mybatis.study.entity.User;
import java.io.IOException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by hg on 2017/7/1.
 */
public class SqlCostInterceptorTest {

    @Test
    public void intercept() throws Exception {

        SqlSessionFactory sessionFactory = null;
        String resource = "mybatis-plugin-test.xml";
        try {
            sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader(resource));
        } catch (IOException e) {
            e.printStackTrace();
        }

        SqlSession sqlSession = sessionFactory.openSession();

        UserDao userMapper = sqlSession.getMapper(UserDao.class);
        User user = userMapper.findUserById(2);
        Assert.assertNotNull("没找到数据", user);
    }

}