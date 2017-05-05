//package com.hg.spring.trans.conf;
//import javax.sql.DataSource;
//import org.apache.commons.dbcp.BasicDataSource;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
///**
// *mysql数据库链接配置
// *
// */
//@Configuration
//@MapperScan("com.hg.spring.trans.mapper")
//public class AppConfig {
//	    @Bean
//	    public DataSource getDataSource() {
//	       BasicDataSource dataSource = new BasicDataSource();
//	       dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//	       dataSource.setUrl("jdbc:mysql://localhost:3306/concretepage");
//	       dataSource.setUsername("root");
//	       dataSource.setPassword("root");
//	       return dataSource;
//	   }
//	   @Bean
//	   public DataSourceTransactionManager transactionManager() {
//	       return new DataSourceTransactionManager(getDataSource());
//	   }
//	   @Bean
//	   public SqlSessionFactory sqlSessionFactory() throws Exception {
//	      SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//	      sessionFactory.setDataSource(getDataSource());
//	      return sessionFactory.getObject();
//	   }
//}