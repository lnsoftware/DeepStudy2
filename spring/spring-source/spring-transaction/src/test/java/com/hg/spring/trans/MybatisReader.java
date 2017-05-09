package com.hg.spring.trans;

import com.hg.spring.trans.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by wangqinghui on 2015/12/23.
 */
public class MybatisReader {
    public static void main(String[] args) throws IOException {
        String resource = "config.xml";

        Reader reader = Resources.getResourceAsReader(resource);

        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(reader);

        SqlSession session = ssf.openSession();

        try {
            User user = (User) session.selectOne("UserDao.findUserById", "2");
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

    }


}
