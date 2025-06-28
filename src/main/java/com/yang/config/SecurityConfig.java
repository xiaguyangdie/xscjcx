package com.yang.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.servlet.http.HttpSession;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    //认证
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    //授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/index").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/student/**").permitAll()
                .antMatchers("/admin/**").permitAll()
                .antMatchers("/teacher/**").permitAll();

        http.formLogin().loginPage("/login");
        //http.formLogin();
        //防止网站攻击：get\post
        http.csrf().disable();
    }
}
