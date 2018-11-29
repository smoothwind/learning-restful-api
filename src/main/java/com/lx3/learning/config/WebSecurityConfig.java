package com.lx3.learning.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,jsr250Enabled = false)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    private final static Log log = LogFactory.getLog(WebSecurityConfig.class);


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        if (log.isTraceEnabled()){
            log.trace("configure httpSecurity ...");
        }

        //默认的spring-security配置会让所有的请求都必须在已登录的状况下访问api;下面这段代码禁止了这种操作
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().anyRequest().permitAll();
    }
}
