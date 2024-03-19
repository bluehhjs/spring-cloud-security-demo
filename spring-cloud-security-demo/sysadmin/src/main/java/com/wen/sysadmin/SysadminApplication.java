package com.wen.sysadmin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.wen.sysadmin.mapper")
@SpringBootApplication
public class SysadminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SysadminApplication.class, args);
    }

}
