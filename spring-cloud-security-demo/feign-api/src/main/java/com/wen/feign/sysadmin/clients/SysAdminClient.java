package com.wen.feign.sysadmin.clients;

import com.wen.feign.sysadmin.domain.SysPermission;
import com.wen.feign.sysadmin.domain.SysUser;
import com.wen.feign.sysadmin.domain.SysRole;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "sysadmin")
public interface SysAdminClient {

    @GetMapping(value = "/api/finUserById/{userId}")
    SysUser findUserById(@PathVariable("userId") String userId);

    @GetMapping(value = "/api//findRolesByUserId/{userId}")
    List<SysRole> findRolesByUserId(@PathVariable("userId") String userId);

    @GetMapping(value="/api/findPermissionByUserId/{userId}")
    List<SysPermission> findPermissionByUserId(@PathVariable String userId);
}
