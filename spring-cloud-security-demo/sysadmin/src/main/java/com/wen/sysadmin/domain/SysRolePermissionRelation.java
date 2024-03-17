package com.wen.sysadmin.domain;

import lombok.Data;

@Data
public class SysRolePermissionRelation {
    private int id;
    private int roleId;
    private int perId;//权限编号
}
