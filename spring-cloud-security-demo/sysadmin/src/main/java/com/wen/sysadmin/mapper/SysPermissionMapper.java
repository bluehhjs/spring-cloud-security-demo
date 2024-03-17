package com.wen.sysadmin.mapper;

import com.wen.sysadmin.domain.SysPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SysPermissionMapper {

    //通过员工id查询员工的权限
    @Select("SELECT p.* FROM sys_permission p, sys_role_permission_relation rpr, sys_user_role_relation srr " +
            "WHERE p.id = rpr.per_id AND rpr.role_id = srr.role_id AND srr.user_id = #{userId}")
    List<SysPermission> findPermissionByUserId(String userId);

}
