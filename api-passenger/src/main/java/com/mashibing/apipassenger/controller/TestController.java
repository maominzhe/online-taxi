package com.mashibing.apipassenger.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Minzhe Mao
 * @Date: 30.07.23 -07 - 30
 * @Description: com.mashibing.apipassenger.controller
 * @Version: 1.0
 **/
@RestController
public class TestController {
    @GetMapping("/test")
    public String test(){
        return "test api passenger";
    }
}
