package com.starrydecisis.stardec;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
// TODO - Disabling Web Security. Delete SecurityConfiguration.java after reenabling
// TODO - Disabling Web Security. Delete SecurityConfiguration.java after reenabling
// TODO - Disabling Web Security. Delete SecurityConfiguration.java after reenabling
// TODO - Disabling Web Security. Delete SecurityConfiguration.java after reenabling
// TODO - Disabling Web Security. Delete SecurityConfiguration.java after reenabling
// TODO - Disabling Web Security. Delete SecurityConfiguration.java after reenabling
// TODO - Disabling Web Security. Delete SecurityConfiguration.java after reenabling
// TODO - Disabling Web Security. Delete SecurityConfiguration.java after reenabling
public class SecurityConfiguration  extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests().antMatchers("/").permitAll();
    }
}