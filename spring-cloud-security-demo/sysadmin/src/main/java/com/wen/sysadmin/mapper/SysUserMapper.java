package com.wen.sysadmin.mapper;


import com.wen.sysadmin.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SysUserMapper {

    @Select("select * from sys_user WHERE id = #{userId}")
    SysUser findUserById(String userId);
}
