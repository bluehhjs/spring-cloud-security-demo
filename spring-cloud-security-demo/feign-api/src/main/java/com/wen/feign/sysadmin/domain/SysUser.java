package com.wen.feign.sysadmin.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SysUser {
    private int id;
    private String userName;
    private String password;
    private int age;
    private String gender;
    private String mobile;
    private String email;
    private LocalDateTime lastLoginTime;//上一次登录时间
    private int enabled;//账号是否可用。默认为1（可用）
    private int notExpired;//是否过期。默认为1（没有过期）
    private int accountNotLocked;//账号是否锁定。默认为1（没有锁定）
    private int credentialsNotExpired;//证书（密码）是否过期。默认为1（没有过期）
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//修改时间
    private int createUser;//创建人
    private int updateUser;//修改人
}
