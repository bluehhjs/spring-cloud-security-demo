package com.wen.sysadmin.web;

import com.wen.sysadmin.domain.SysRole;
import com.wen.sysadmin.mapper.SysRoleMapper;
import com.wen.sysadmin.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName: SysRoleController
 * @Description: 查询角色接口
 * @Author: 文
 * @Data 2024/3/17
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/api")
public class SysRoleController {

    @Autowired
    SysRoleMapper sysRoleMapper;

    /**
     * 查询该用户的所有角色
     */
    @GetMapping(value = "/findRolesByUserId/{userId}")
    public List<SysRole> findRolesByUserId(@PathVariable("userId") String userId){
        return sysRoleMapper.findRolesByUserId(userId);
    }


}
