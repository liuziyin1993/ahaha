package com.aisile.user.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    public void configure(HttpSecurity http) throws Exception{
        //括号中/**为放过所有
        http.authorizeRequests().antMatchers("/**").
                    permitAll().anyRequest().authenticated()
                .and().csrf().disable();
    }
}

