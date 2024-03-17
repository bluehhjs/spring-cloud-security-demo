package com.wen.authentication.server.auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyPasswordEncoder implements PasswordEncoder {

    /**
     * 密码加密方法
     * @param rawPassword 密码的明文
     * @return 密码加密后的密文
     */
    @Override
    public String encode(CharSequence rawPassword) {
        System.out.println("自定义密码器 -encode方法执行");
        return rawPassword.toString();
    }

    /**
     * 校验密码明文的密文是否相同的方法
     * @param rawPassword 明文密码，是客户端登陆时传递的
     * @param encodedPassword 密码密文，是服务器中存储的。一般保存在数据库中。
     * @return 加密后的明文与密文比较的结果（布尔类型）
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        //先使用encode方法，用相同的加密策略加密明文，再对比密文。
        return encode(rawPassword).equals(encodedPassword);
    }

    /**
     * 是否需要升级密码解析策略。
     * @param encodedPassword
     * @return
     */
    @Override
    public boolean upgradeEncoding(String encodedPassword) {
        return PasswordEncoder.super.upgradeEncoding(encodedPassword);
    }
}
