package com.wen.authorization.server.provider.oauth2.utils;

import com.wen.feign.sysadmin.domain.SysPermission;
import com.wen.feign.sysadmin.domain.SysRole;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: AuthorityUtils
 * @Description: 封装了一些容易重复的操作
 * @Author: 文
 * @Data 2024/3/21
 * @Version 1.0
 */

public class SecurityUtils {

    //构建用户的权限列表
    public static List<String> buildAuthorities(List<SysRole> roles, List<SysPermission> permissions) {
        List<String> authorities = new ArrayList<>();
        for(SysRole role : roles){
            authorities.add("ROLE_" + role.getRoleName());
        }
        for(SysPermission permission : permissions){
            authorities.add(permission.getPermit());
        }
        return authorities;
    }

}
