package com.zhiyin.oauth2.oauthserver.config.db;

import com.zhiyin.oauth2.oauthserver.user.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableAspectJAutoProxy(proxyTargetClass=true)
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfigJdbc extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    public void globalUserDetails(final AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().
//                withUser("root").password("root").roles("ADMIN").
//                and().
//                withUser("john").password("123").roles("USER").
//                and().
//
//        使用sha加密
//        ShaPasswordEncoder encoder = new ShaPasswordEncoder();
//md5加密
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        auth.userDetailsService(authenticationService).passwordEncoder(encoder);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        // @formatter:off

//        http.authorizeRequests()
//            .antMatchers("/login").permitAll()
//            .anyRequest().authenticated()
//            .and().formLogin().permitAll()
//            ;

        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic().disable();

//        http
//                .formLogin().loginPage("/login").permitAll()
//                .and()
//                .requestMatchers().antMatchers("/login", "/oauth/authorize", "/oauth/confirm_access")
//                .and()
//                .httpBasic().disable()
//                .authorizeRequests().anyRequest().authenticated();
        // @formatter:on

    }


}
