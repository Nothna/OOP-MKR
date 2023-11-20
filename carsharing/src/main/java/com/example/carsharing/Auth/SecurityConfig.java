package com.example.carsharing.Auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/posts/**").permitAll() // Allow access to public URLs
                .anyRequest().authenticated() // Require authentication for other URLs
                .and()
                .formLogin() // Enable form-based login
                .loginPage("/login") // Custom login page
                .permitAll() // Allow access to login page
                .and()
                .logout() // Enable logout
                .permitAll();
    }
}
