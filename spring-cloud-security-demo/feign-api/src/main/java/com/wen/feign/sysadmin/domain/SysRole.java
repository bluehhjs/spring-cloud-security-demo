package com.wen.feign.sysadmin.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SysRole {
    private int id;
    private String roleName;
    private LocalDateTime genTime;
    private String description;
}
