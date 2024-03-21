package com.wen.authorization.server.provider.oauth2.granter;

import com.wen.authorization.server.provider.oauth2.MobileUserDetailsService;
import com.wen.feign.sysadmin.clients.SysAdminClient;
import com.wen.feign.sysadmin.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: MobileAuthenticationProvider
 * @Description: 手机验证码登录的provider
 * @Author: 文
 * @Data 2024/3/21
 * @Version 1.0
 */

public class MobileAuthenticationProvider implements AuthenticationProvider {

    private final MobileUserDetailsService mobileUserDetailsService;

    public MobileAuthenticationProvider(MobileUserDetailsService mobileUserDetailsService) {
        this.mobileUserDetailsService = mobileUserDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        MobileAuthenticationToken mobileAuthenticationToken = (MobileAuthenticationToken)authentication;

        String mobile = mobileAuthenticationToken.getMobile();
        String mobileCode = mobileAuthenticationToken.getMobileCode();


        // 模拟从redis中读取手机号对应的验证码
        Map<String, String> dataFromRedis = new HashMap<>();
        dataFromRedis.put("code", "6789");

        // 判断验证码是否一致
        if (!mobileCode.equals(dataFromRedis.get("code"))) {
            throw new BadCredentialsException("验证码错误");
        }

        System.out.println("你好哇");

        // 如果验证码一致，从数据库中读取该手机号对应的用户信息
        UserDetails loadedUser = mobileUserDetailsService.loadUserByMobileMobile(mobile);

        if (loadedUser == null) {
            throw new UsernameNotFoundException("用户不存在");
        } else {

            // 创建包含用户权限的认证对象
            return new MobileAuthenticationToken(loadedUser.getAuthorities(),null,loadedUser);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(MobileAuthenticationToken.class);
    }

}
