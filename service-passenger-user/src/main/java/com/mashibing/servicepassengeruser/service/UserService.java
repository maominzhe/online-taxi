package com.mashibing.servicepassengeruser.service;

import com.mashibing.internalcommon.dto.ResponseResult;
import com.mashibing.servicepassengeruser.dto.PassengerUser;
import com.mashibing.servicepassengeruser.mapper.PassengerUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

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
    @Autowired
    private PassengerUserMapper passengerUserMapper;
    public ResponseResult loginOrRegister(String passengerPhone){
        System.out.println("service-passenger-userservice..with phone number: "+ passengerPhone);
        // query user info with phone number
        Map<String, Object> map = new HashMap<>();
        map.put("passenger_phone", passengerPhone);
        List<PassengerUser> passengerUsers = passengerUserMapper.selectByMap(map);
        //System.out.println(passengerUsers.size()==0?"no records..":passengerUsers.get(0).getPassengerPhone());

        // check if user exists
        if (passengerUsers.size()==0){
            // insert user
            LocalDateTime now = LocalDateTime.now();
            PassengerUser passengerUser = new PassengerUser();
            passengerUser.setPassengerPhone(passengerPhone);
            passengerUser.setPassengerName("Minzhe");
            passengerUser.setPassengerGender((byte) 0);
            passengerUser.setGmtCreate(now);
            passengerUser.setGmtModified(now);
            passengerUser.setState((byte) 0);

            passengerUserMapper.insert(passengerUser);


        }


        return ResponseResult.success();
    }
}
