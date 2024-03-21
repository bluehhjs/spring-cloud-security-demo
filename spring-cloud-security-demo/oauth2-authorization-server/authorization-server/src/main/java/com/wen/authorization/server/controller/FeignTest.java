package com.wen.authorization.server.controller;

import com.wen.feign.sysadmin.clients.SysAdminClient;
import com.wen.feign.sysadmin.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: FeignTest
 * @Author: 文
 * @Data 2024/3/18
 * @Version 1.0
 */

@RestController
public class FeignTest {

    @Autowired
    SysAdminClient sysAdminClient;

    @GetMapping(value="/user/{id}")
    public SysUser getUser(@PathVariable String id){
        return sysAdminClient.findUserByUserId(id);
    }

}
