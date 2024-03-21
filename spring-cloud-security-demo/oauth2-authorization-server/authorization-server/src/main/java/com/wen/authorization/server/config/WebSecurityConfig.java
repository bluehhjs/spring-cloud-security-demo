package com.wen.authorization.server.config;

import com.wen.authorization.server.provider.oauth2.MobileUserDetailsService;
import com.wen.authorization.server.provider.oauth2.granter.MobileAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @ClassName: SecurityConfig
 * @Description:
 * @Author: 文
 * @Data 2024/3/20
 * @Version 1.0
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    @Qualifier("mobileUserDetailsService")
    private MobileUserDetailsService mobileUserDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/login/*").permitAll()
                        .anyRequest().authenticated());

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }

    @Bean
    public MobileAuthenticationProvider mobileAuthenticationProvider() {
        return new MobileAuthenticationProvider(mobileUserDetailsService);
    }

    @Bean
    public AuthenticationManager authenticationManager(){
        List<AuthenticationProvider> authenticationProviders = new ArrayList<>();
        authenticationProviders.add(mobileAuthenticationProvider());
        authenticationProviders.add(daoAuthenticationProvider());
        return  new ProviderManager(authenticationProviders);

    }

    //将传入的 AuthenticationProvider 数组转换为一个 List 返回。
    private List<AuthenticationProvider> getProviders(AuthenticationProvider... providers) {
        return Arrays.asList(providers);
    }

    /**
     * 创建一个PasswordEncoder类型bean对象
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        //构造方法可以传递整型参数，范围4~31之间，数字越大，强度越高，安全性越好，性能就越差
        return new BCryptPasswordEncoder();
    }


}