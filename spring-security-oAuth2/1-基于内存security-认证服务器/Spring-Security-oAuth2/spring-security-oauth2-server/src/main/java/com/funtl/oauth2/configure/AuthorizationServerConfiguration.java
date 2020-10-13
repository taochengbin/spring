package com.funtl.oauth2.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    // 注入 WebSecurityConfiguration 中配置的 BCryptPasswordEncoder
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 配置客户端
        clients
            // 使用内存设置
            .inMemory()
            // client_id
            .withClient("client")
            // client_secret
            .secret(passwordEncoder.encode("secret"))
            // 授权类型
            .authorizedGrantTypes("authorization_code")
            // 授权范围
            .scopes("app")
            // 注册回调地址
            .redirectUris("http://127.0.0.1:4444/testMemoryRequest");
    }
}
