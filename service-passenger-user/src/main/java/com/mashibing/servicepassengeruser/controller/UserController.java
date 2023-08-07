package com.mashibing.servicepassengeruser.controller;

import com.mashibing.internalcommon.dto.ResponseResult;
import com.mashibing.internalcommon.request.VerificationCodeDTO;
import com.mashibing.servicepassengeruser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: Minzhe Mao
 * @Date: 01.08.23 -08 - 01
 * @Description: com.mashibing.servicepassengeruser.controller
 * @Version: 1.0
 **/
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/user")
    public ResponseResult loginOrReg(@RequestBody VerificationCodeDTO verificationCodeDTO){
        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        System.out.println("service-passenger-user: passenger phone is " + passengerPhone);
        return userService.loginOrRegister(passengerPhone);
    }

    @GetMapping("/user/{phone}")
    public ResponseResult getUser(@PathVariable("phone") String passengerPhone){
        System.out.println("service-passenger-user: phone:"+passengerPhone);
        return userService.getUserByPhone(passengerPhone);

//    @GetMapping("/user/")
//    public ResponseResult getUser(@RequestBody VerificationCodeDTO verificationCodeDTO){
//        String passengerPhone = verificationCodeDTO.getPassengerPhone();
//        return userService.getUserByPhone(passengerPhone);
    }
}
