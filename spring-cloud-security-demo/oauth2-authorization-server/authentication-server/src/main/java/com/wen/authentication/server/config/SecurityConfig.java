package com.wen.authentication.server.config;

import com.wen.authentication.server.auth.MyPasswordEncoder;
import com.wen.authentication.server.auth.MyUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.RememberMeConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

/**
 * @ClassName: SecurityConfig
 * @Description: 用户名密码登录认证配置类
 * @Author: 文
 * @Data 2024/3/17
 * @Version 1.0
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    MyUserDetailsServiceImpl myUserDetailsService;

    @Autowired
    MyPasswordEncoder myPasswordEncoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // 配置表单登录，定义登录页面的相关参数和处理逻辑
        Customizer<FormLoginConfigurer<HttpSecurity>> formLoginConfigurerCustomizer = httpSecurityFormLoginConfigurer -> {
            httpSecurityFormLoginConfigurer
                    .loginProcessingUrl("/userLogin")
                    .usernameParameter("username")
                    .passwordParameter("password");
        };


        // 配置请求授权规则，任何请求都需要认证通过
        http.authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers(new AntPathRequestMatcher("/login","GET")).permitAll()//访问/login地址时，不做认证授权验证
                        .requestMatchers(new AntPathRequestMatcher("/userLogin","POST")).permitAll()//访问/userLogin地址时，不做认证授权验证
                        .requestMatchers(new AntPathRequestMatcher("/login/sms","GET")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/js/**")).hasAuthority("user:manage")//设置请求地址是/js/**时，可以随意访问
                        .requestMatchers(new AntPathRequestMatcher("/**")).hasRole("超级管理员")
                        .anyRequest().authenticated())
                .formLogin(formLoginConfigurerCustomizer);


        return http.build();

    }

    @Bean
    @ConditionalOnClass
    public AuthenticationManager authenticationManager(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return myUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return myPasswordEncoder;
    }


}
