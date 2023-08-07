package com.mashibing.apipassenger.service;

import com.mashibing.apipassenger.remote.ServicePassengerUserClient;
import com.mashibing.internalcommon.dto.PassengerUser;
import com.mashibing.internalcommon.dto.ResponseResult;
import com.mashibing.internalcommon.dto.TokenResult;
import com.mashibing.internalcommon.response.TokenResponse;
import com.mashibing.internalcommon.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    ServicePassengerUserClient servicePassengerUserClient;

    public ResponseResult getUserByAccessToken(String accessToken){
        log.info("accessToken:"+accessToken);
        // parse accessToke and get phone number
        log.info("accessToken: "+ accessToken);
        TokenResult tokenResult = JwtUtils.checkToken(accessToken);
        assert tokenResult != null;
        String phone = tokenResult.getPhone();
        log.info("passenger phone: "+phone);

        // query passenger info with phone number
        ResponseResult<PassengerUser> userByPhone = servicePassengerUserClient.getUserByPhone(phone);
        return ResponseResult.success(userByPhone.getData());
    }

}
