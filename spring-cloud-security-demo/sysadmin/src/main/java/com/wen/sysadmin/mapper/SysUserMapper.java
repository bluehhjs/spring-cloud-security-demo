package com.wen.sysadmin.mapper;


import com.wen.sysadmin.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SysUserMapper {

    @Select("SELECT * FROM sys_user WHERE id = #{userId}")
    SysUser findUserById(String userId);

    @Select("SELECT * FROM sys_user WHERE mobile = #{mobile}")
    SysUser findUserByMobile(String mobile);
}
