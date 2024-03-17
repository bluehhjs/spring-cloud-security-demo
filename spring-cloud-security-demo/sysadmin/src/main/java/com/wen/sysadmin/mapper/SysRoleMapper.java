package com.wen.sysadmin.mapper;

import com.wen.sysadmin.domain.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SysRoleMapper {

    //根据用户id查询用户的角色
    @Select("SELECT r.* FROM sys_role r, sys_user_role_relation srr WHERE r.id = srr.role_id AND srr.user_id = #{userId}")
    List<SysRole> findRolesByUserId(String UserId);

}
