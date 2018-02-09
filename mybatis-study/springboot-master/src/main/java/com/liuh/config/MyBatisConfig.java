package com.liuh.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;


/**
 * Created by liuhui.
 */
@MapperScan(basePackages = "com.liuh.*.mapper")
@Configuration
public class MyBatisConfig {

    @Autowired
    private DruidConfig druidConfig;


    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(druidConfig.mysqlDataSource());
        sqlSessionFactoryBean.setTypeAliasesPackage("com.liuh");

        //分页插件
       /* PagePlugin pagePlugin = new PagePlugin();
        Properties properties = new Properties();
        properties.setProperty("dialect", "mysql");
        properties.setProperty("pageSqlId", ".*query.*");
        pagePlugin.setProperties(properties);

        //添加插件
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pagePlugin});*/

        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mybatis/*/*Mapper.xml"));
            return sqlSessionFactoryBean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean
    public DataSourceTransactionManager transactionManager() throws Exception {
        return new DataSourceTransactionManager(druidConfig.mysqlDataSource());
    }

}
