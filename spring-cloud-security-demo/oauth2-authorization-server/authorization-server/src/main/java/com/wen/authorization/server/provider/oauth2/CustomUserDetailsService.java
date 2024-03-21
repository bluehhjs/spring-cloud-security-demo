package com.wen.authorization.server.provider.oauth2;

import com.wen.authorization.server.provider.oauth2.utils.SecurityUtils;
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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: MyUserDetailsServiceImpl
 * @Description: 账号密码登录
 * @Author: 文
 * @Data 2024/3/17
 * @Version 1.0
 */


@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private final SysAdminClient sysAdminClient;

    public CustomUserDetailsService(SysAdminClient sysAdminClient) {
        this.sysAdminClient = sysAdminClient;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        SysUser sysUser = findUserByUserId(userId);
        validateUser(sysUser);
        List<SysRole> roles = findRolesByUserId((long) sysUser.getId());
        List<SysPermission> permissions = findPermissionByUserId((long) sysUser.getId());

        //构建用户的权限列表
        List<String> authorities = SecurityUtils.buildAuthorities(roles, permissions);

        return createUserDetails(sysUser, userId, authorities);
    }

    private SysUser findUserByUserId(String userId) {
        return sysAdminClient.findUserByUserId(userId);
    }

    private void validateUser(SysUser sysUser) {
        if (sysUser == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
    }

    private List<SysRole> findRolesByUserId(Long userId) {
        return sysAdminClient.findRolesByUserId(String.valueOf(userId));
    }

    private List<SysPermission> findPermissionByUserId(Long userId) {
        return sysAdminClient.findPermissionByUserId(String.valueOf(userId));
    }


    private UserDetails createUserDetails(SysUser sysUser, String userId, List<String> authorities) {
        return new User(
                userId,
                sysUser.getPassword(),
                AuthorityUtils.createAuthorityList(authorities.toArray(new String[0]))
        );
    }
}
