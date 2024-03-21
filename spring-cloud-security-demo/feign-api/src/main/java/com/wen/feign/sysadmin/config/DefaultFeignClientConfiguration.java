package com.wen.feign.sysadmin.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * @ClassName: DefaultFeignConfiguration
 * @Description:
 * @Author: 文
 * @Data 2024/3/15
 * @Version 1.0
 */

public class DefaultFeignClientConfiguration {
    @Bean
    public Logger.Level feignLogLevel(){
        return Logger.Level.BASIC;
    }
}

