package com.hg.mybatis.study;

import com.hg.mybatis.study.dao.UserDao;
import com.hg.mybatis.study.entity.User;
import java.io.IOException;
import java.util.Map;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by hg on 2015/12/23.
 */
public class UserDaoTest {

    @Test
    public void typeAliase() {
        Configuration con = getSessionFactory().getConfiguration();
        Map<String, Class<?>> typeMap = con.getTypeAliasRegistry().getTypeAliases();
        for (Map.Entry<String, Class<?>> entry : typeMap.entrySet()) {
            System.out.println(entry.getKey() + " ================> " + entry.getValue().getSimpleName());
        }
    }

    @Test
    public void findUserById() {
        SqlSession sqlSession = getSessionFactory().openSession();

        UserDao userMapper = sqlSession.getMapper(UserDao.class);
        User user = userMapper.findUserById(2);
        Assert.assertNotNull("没找到数据", user);
    }

    //Mybatis 通过SqlSessionFactory获取SqlSession, 然后才能通过SqlSession与数据库进行交互
    private static SqlSessionFactory getSessionFactory() {
        SqlSessionFactory sessionFactory = null;
        String resource = "config.xml";
        try {
            sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader(resource));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }
}
