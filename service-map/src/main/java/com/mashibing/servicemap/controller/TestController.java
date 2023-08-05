package com.mashibing.servicemap.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Minzhe Mao
 * @Date: 05.08.23 -08 - 05
 * @Description: com.mashibing.servicemap.Controller
 * @Version: 1.0
 **/
@RestController
public class TestController {
    @GetMapping("/test")
    public String test(){
        return "service map..";
    }
}
