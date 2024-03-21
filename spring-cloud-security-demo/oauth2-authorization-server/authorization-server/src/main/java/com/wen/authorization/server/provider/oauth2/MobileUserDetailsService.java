package com.wen.authorization.server.provider.oauth2;

import com.wen.authorization.server.provider.oauth2.utils.SecurityUtils;
import com.wen.feign.sysadmin.clients.SysAdminClient;
import com.wen.feign.sysadmin.domain.SysPermission;
import com.wen.feign.sysadmin.domain.SysRole;
import com.wen.feign.sysadmin.domain.SysUser;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: MobileUserDetailsService
 * @Description: 手机号UserDetails
 * @Author: 文
 * @Data 2024/3/21
 * @Version 1.0
 */

@Service("mobileUserDetailsService")
public class MobileUserDetailsService implements UserDetailsService {

    private final SysAdminClient sysAdminClient;

    public MobileUserDetailsService(SysAdminClient sysAdminClient) {
        this.sysAdminClient = sysAdminClient;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        throw new UnsupportedOperationException("This method is not supported.");
    }

    public UserDetails loadUserByMobileMobile(String mobile) {
        SysUser sysUser = sysAdminClient.findUserByMobile(mobile);
        String sysUserId = String.valueOf(sysUser.getId());

        List<SysRole> roles = findRolesByUserId(sysUserId);
        List<SysPermission> permissions = findPermissionByUserId(sysUserId);

        //构建用户的权限列表
        List<String> authorities = SecurityUtils.buildAuthorities(roles, permissions);

        return createUserDetails(sysUser, authorities);
    }


    private List<SysRole> findRolesByUserId(String userId) {
        return sysAdminClient.findRolesByUserId(userId);
    }

    private List<SysPermission> findPermissionByUserId(String userId) {
        return sysAdminClient.findPermissionByUserId(userId);
    }

    private UserDetails createUserDetails(SysUser sysUser, List<String> authorities) {
        return new User(
                sysUser.getMobile(), // 登录用户的账号
                sysUser.getPassword(),
                AuthorityUtils.createAuthorityList(authorities.toArray(new String[0]))
        );
    }
}
