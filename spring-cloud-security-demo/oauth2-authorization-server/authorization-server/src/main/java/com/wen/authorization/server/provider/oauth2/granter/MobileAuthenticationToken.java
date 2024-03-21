package com.wen.authorization.server.provider.oauth2.granter;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

import java.io.Serial;
import java.util.Collection;

/**
 * @ClassName: MobileAuthenticationToken
 * @Description: 手机验证码登录token验证类
 * @Author: 文
 * @Data 2024/3/21
 * @Version 1.0
 */
public class MobileAuthenticationToken extends AbstractAuthenticationToken {

    @Serial
    private static final long serialVersionUID = 20240321L;
    private Object principal;
    private Object credentials;
    private String mobile;
    private String mobileCode;

    public MobileAuthenticationToken(String mobile, String mobileCode) {
        super(null);
        this.mobile = mobile;
        this.mobileCode = mobileCode;
        this.setAuthenticated(false);
    }

    public MobileAuthenticationToken(Collection<? extends GrantedAuthority> authorities, Object principal, Object credentials) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
    }


    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    public String getMobile() {
        return this.mobile;
    }

    public String getMobileCode() {
        return this.mobileCode;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException("不能将此令牌设置为受信任状态 - 请使用接受 GrantedAuthority 列表的构造方法");
        }
        super.setAuthenticated(false);
    }


    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        this.credentials = null;
    }
}

