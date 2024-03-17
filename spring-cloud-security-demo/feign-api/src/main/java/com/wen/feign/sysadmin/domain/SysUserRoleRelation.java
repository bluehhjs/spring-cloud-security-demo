package com.wen.feign.sysadmin.domain;

import lombok.Data;

@Data
public class SysUserRoleRelation {
    private int id;
    private int userId;
    private int roleId;
}
