package com.wen.authorization.server;

import com.wen.feign.sysadmin.clients.SysAdminClient;
import com.wen.feign.sysadmin.config.DefaultFeignClientConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients(defaultConfiguration = DefaultFeignClientConfiguration.class,clients = {SysAdminClient.class})
@SpringBootApplication
public class AuthorizationServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorizationServerApplication.class, args);
    }


}
