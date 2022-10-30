package com.paymentchain.customer;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    // Autenticacion
    protected void configure(AuthenticationManagerBuilder managerBuilder) throws Exception{
        managerBuilder.inMemoryAuthentication()
        .withUser("user").password("{noop}user").roles("USER")
        .and()
        .withUser("admin").password("{noop}admin").roles("USER", "ADMIN");
    }

    // Autorizacion
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
        .csrf().disable()
        .authorizeRequests()
        .antMatchers("/api/**").hasAnyRole("USER", "ADMIN")
        .and()
        .httpBasic();
    }

}
