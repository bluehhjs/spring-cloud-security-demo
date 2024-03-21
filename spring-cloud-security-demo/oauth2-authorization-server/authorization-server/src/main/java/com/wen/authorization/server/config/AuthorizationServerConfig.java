package com.wen.authorization.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

/**
 * @ClassName: AuthorizationServerConfig
 * @Description:
 * @Author: 文
 * @Data 2024/3/21
 * @Version 1.0
 */

@Configuration
@EnableWebSecurity
public class AuthorizationServerConfig {

    /**
     * 配置自定义的granter,手机号验证码登陆
     *
     * @param endpoints
     * @return
     * @auth joe_chen
     */



}
