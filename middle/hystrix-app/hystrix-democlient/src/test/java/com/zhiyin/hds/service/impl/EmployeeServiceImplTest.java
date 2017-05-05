package com.zhiyin.hds.service.impl;

import com.zhiyin.hds.service.IEmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class EmployeeServiceImplTest {

    @Autowired
    private IEmployeeService employeeService;

    @Test
    public void list() throws Exception {
        assertThat(this.employeeService.list(),
                is("Fallback call, seems employee service is down"));
    }

    @Configuration
    @EnableAutoConfiguration
    @EnableHystrix
    @PropertySource(value = {"classpath:application.yml"})
    public static class SpringConfig {

        @Bean
        public IEmployeeService list() {
            return new EmployeeServiceImpl();
        }
    }
}
