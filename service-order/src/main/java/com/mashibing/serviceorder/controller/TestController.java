package com.mashibing.serviceorder.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Minzhe Mao
 * @Date: 07.08.23 -08 - 07
 * @Description: com.mashibing.serviceorder.controller
 * @Version: 1.0
 **/
@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "service-order-test..";
    }
}
