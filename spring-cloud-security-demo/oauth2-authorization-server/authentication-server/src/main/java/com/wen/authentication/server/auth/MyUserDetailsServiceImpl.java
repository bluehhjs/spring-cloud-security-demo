package com.wen.authentication.server.auth;

import com.wen.feign.sysadmin.clients.SysAdminClient;
import com.wen.feign.sysadmin.domain.SysPermission;
import com.wen.feign.sysadmin.domain.SysRole;
import com.wen.feign.sysadmin.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: MyUserDetailsServiceImpl
 * @Description: 账号密码登录
 * @Author: 文
 * @Data 2024/3/17
 * @Version 1.0
 */


@Component
public class MyUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    SysAdminClient sysAdminClient;

    /**
     * 方法实现内容是： 根据用户名查询用户对象和用户的权限列表
     * @param userId 用户的登录账号
     * @return UserDetails接口类型对象
     * @throws UsernameNotFoundException 用户名不存在异常
     */
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        System.out.println("自定义登录服务 - loadUserByUsername方法执行");

        // 根据登录方式，查询用户
        SysUser sysUser = null;

        // 如果是用户名登录
        sysUser = sysAdminClient.finUserById(userId);

        //判断该账号是否存在
        if(sysUser == null) {
            System.out.println("用户：" + userId + "不存在");
            //用户不存在
            throw new UsernameNotFoundException("用户名或密码错误");
        }

        //开始查询已登录的用户的权限集合
        String sysUserId = String.valueOf(sysUser.getId());
        //查询角色
        List<SysRole> roles = sysAdminClient.findRolesByUserId(sysUserId);
        System.out.println("roles:"+roles);
        //查询权限
        List<SysPermission> permissions = sysAdminClient.findPermissionByUserId(sysUserId);

        //定义一个字符串泛型的List集合
        List<String> authorities = new ArrayList<>();
        for(SysRole role : roles){
            //角色名称纳入权限管理，必须增加前缀 ROLE_
            authorities.add("ROLE_" + role.getRoleName());
        }

        for(SysPermission permission : permissions){
            //权限字符串描述，不需要特定的任何前后缀
            authorities.add(permission.getPermit());
        }

        System.out.println("sysAccount:"+ sysUser);
        //返回UserDetails接口类型对象
        return new User(
                userId,//登录用户的账号
                sysUser.getPassword(),//登录密码，是服务端存储的密文
                AuthorityUtils.createAuthorityList(authorities.toArray(new String[0]))//通过工具把字符串权限转换为Security定义的权限
        );

    }
}
