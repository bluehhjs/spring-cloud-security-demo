package com.wen.authorization.server.web;

import com.wen.authorization.server.provider.oauth2.granter.MobileAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: LoginController
 * @Description: 登录接口
 * @Author: 文
 * @Data 2024/3/21
 * @Version 1.0
 */

@RequestMapping("/login")
@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 用户名密码登录
     * @param username
     * @param password
     * @return
     */
    @GetMapping("/usernamePwd")
    public String usernamePwd(String username, String password) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticate = null;
        try {
            authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        } catch (Exception e) {
            e.printStackTrace();
            return "登陆失败";
        }

        return"登陆成功";
    }

    /**
     * 手机验证码登录
     * @param mobile
     * @param mobileCode
     * @return
     */
    @GetMapping("/mobileCode")
    public String mobileCode(String mobile, String mobileCode) {
        System.out.println("你好");
        MobileAuthenticationToken mobileAuthenticationToken = new MobileAuthenticationToken(mobile, mobileCode);
        Authentication authenticate = null;
        try {
            authenticate = authenticationManager.authenticate(mobileAuthenticationToken);
        } catch (Exception e) {
            e.printStackTrace();
            return "验证码错误";
        }
        return "登陆成功";
    }

}
