package com.mashibing.apipassenger.service;

import com.mashibing.internalcommon.dto.PassengerUser;
import com.mashibing.internalcommon.dto.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Auther: Minzhe Mao
 * @Date: 04.08.23 -08 - 04
 * @Description: com.mashibing.apipassenger.service
 * @Version: 1.0
 **/
@Service
@Slf4j
public class UserService {

    public ResponseResult getUserByAccessToken(String accessToken){
        log.info("accessToken:"+accessToken);
        // parse accessToke and get phone number
        PassengerUser passengerUser = new PassengerUser();
        passengerUser.setPassengerName("mao");
        passengerUser.setProfilePhoto("pic");


        // query user info based on phone number


        return ResponseResult.success(passengerUser);
    }
}
