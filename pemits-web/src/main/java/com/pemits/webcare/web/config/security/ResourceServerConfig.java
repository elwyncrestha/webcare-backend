package com.pemits.webcare.web.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author Elvin Shrestha on 6/21/2020
 * TODO: Migrate Resource Server from Spring Security OAuth to Spring Security
 * https://github.com/spring-projects/spring-security/wiki/OAuth-2.0-Migration-Guide
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("resources.demo.com").stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/oauth/token")
            .permitAll()
            .antMatchers(HttpMethod.POST, "/v1/appointment")
            .permitAll()
            .antMatchers(HttpMethod.GET, "/v1/departments/all")
            .permitAll()
            .antMatchers(HttpMethod.POST, "/v1/doctors/list/all")
            .permitAll()
            .antMatchers(HttpMethod.GET, "/v1/patient/*")
            .permitAll()
            .antMatchers(HttpMethod.POST, "/v1/helpdesk")
            .permitAll()
            .antMatchers(HttpMethod.POST, "/v1/patient")
            .anonymous()
            .antMatchers(HttpMethod.POST, "/v1/users/resetPassword/verify")
            .permitAll()
            .antMatchers(HttpMethod.POST, "/v1/users/resetPassword")
            .permitAll()
            .antMatchers("/v1/**")
            .authenticated()
            .and()
            .logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/oauth/logout"));
    }
}
