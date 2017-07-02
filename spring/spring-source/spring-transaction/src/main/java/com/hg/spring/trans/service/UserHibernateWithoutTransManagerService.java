//package com.hg.spring.trans.service;
//
//import org.springframework.beans.factorybean.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Service;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.orm.hibernate3.HibernateTemplate;
//import org.apache.commons.dbcp.BasicDataSource;
//import user.User;
//
//@Service("service2")
//public class UserHibernateWithoutTransManagerService {
//    @Autowired
//    private HibernateTemplate hibernateTemplate;
//
//    public void addScore(String userName,int toAdd){
//        User user = (User)hibernateTemplate.get(User.class,userName);
//        user.setScore(user.getScore()+toAdd);
//        hibernateTemplate.update(user);
//    }
//
//    public static void main(String[] args) {
//        //参考UserJdbcWithoutTransManagerService相应代码
//        …
//    }
//}