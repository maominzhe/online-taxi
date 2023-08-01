package com.mashibing.servicepassengeruser.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Minzhe Mao
 * @Date: 01.08.23 -08 - 01
 * @Description: com.mashibing.servicepassengeruser.controller
 * @Version: 1.0
 **/
@RestController
public class TestController {
    @GetMapping
    public String test(){
        return "passenger user service test controller.. ";
    }
}
