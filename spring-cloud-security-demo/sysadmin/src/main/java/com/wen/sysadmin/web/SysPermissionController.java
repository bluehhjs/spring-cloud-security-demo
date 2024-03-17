package com.wen.sysadmin.web;

import com.wen.sysadmin.domain.SysPermission;
import com.wen.sysadmin.mapper.SysPermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName: SysPermissionController
 * @Description: 用户查询权限接口
 * @Author: 文
 * @Data 2024/3/17
 * @Version 1.0
 */
@RestController
@RequestMapping(value="/api")
public class SysPermissionController {

    @Autowired
    SysPermissionMapper sysPermissionMapper;

    @GetMapping(value="/findPermissionByUserId/{userId}")
    public List<SysPermission> findPermissionByUserId(@PathVariable String userId){
        return sysPermissionMapper.findPermissionByUserId(userId);
    }
}
