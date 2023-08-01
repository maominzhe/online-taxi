package com.mashibing.servicepassengeruser.service;

import com.mashibing.internalcommon.dto.ResponseResult;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Auther: Minzhe Mao
 * @Date: 01.08.23 -08 - 01
 * @Description: com.mashibing.servicepassengeruser.service
 * @Version: 1.0
 **/
@Service
public class UserService {
    public ResponseResult loginOrRegister(String passengerPhone){
        System.out.println("service-passenger-userservice..with phone number: "+ passengerPhone);
        // query user info with phone number
        Map<String,Object> map = new HashedMap();
        map.put("passenger_phone", passengerPhone);



        // check if user exists


        return ResponseResult.success();
    }
}
