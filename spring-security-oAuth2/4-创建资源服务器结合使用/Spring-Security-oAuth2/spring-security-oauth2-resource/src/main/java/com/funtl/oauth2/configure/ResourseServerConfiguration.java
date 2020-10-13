package com.funtl.oauth2.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class ResourseServerConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .exceptionHandling()
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            // 以下为配置所需保护的资源路径及权限，需要与认证服务器配置的授权部分对应
            .antMatchers("/").hasAuthority("System")
            .antMatchers("/view/**").hasAuthority("SystemContentView")
            .antMatchers("/insert/**").hasAuthority("SystemContentInsert")
            .antMatchers("/update/**").hasAuthority("SystemContentUpdate")
            .antMatchers("/delete/**").hasAuthority("SystemContentDelete");
    }

}
