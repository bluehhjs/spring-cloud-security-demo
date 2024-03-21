package com.wen.authorization.server.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: TestController
 * @Description:
 * @Author: 文
 * @Data 2024/3/20
 * @Version 1.0
 */
@RestController
public class TestController {

    @GetMapping("/getArticle")
    public String getArticle(){
        return "文章";
    }
}
