package com.wen.sysadmin.web;

import com.wen.sysadmin.domain.SysUser;
import com.wen.sysadmin.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: SysUserController
 * @Description: 查询用户接口
 * @Author: 文
 * @Data 2024/3/17
 * @Version 1.0
 */

@RestController
@RequestMapping(value = "/api")
public class SysUserController {

    @Autowired
    SysUserMapper sysUserMapper;

    /**
     * 返回用户信息
     */
    @GetMapping(value = "/finUserById/{userId}")
    public SysUser finUserById(@PathVariable("userId") String userId){
        return sysUserMapper.findUserById(userId);
    }

    @GetMapping(value = "/findUserByMobile/{mobile}")
    public SysUser findUserByMobile(@PathVariable("mobile") String mobile){
        return sysUserMapper.findUserByMobile(mobile);
    }

}
