package com.mashibing.apipassenger.service;

import com.mashibing.apipassenger.remote.ServiceVerificationcodeClient;
import com.mashibing.internalcommon.constant.CommonStatusEnum;
import com.mashibing.internalcommon.dto.ResponseResult;
import com.mashibing.internalcommon.response.NumberCodeResponse;
import com.mashibing.internalcommon.response.TokenResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: Minzhe Mao
 * @Date: 30.07.23 -07 - 30
 * @Description: com.mashibing.apipassenger.service
 * @Version: 1.0
 **/
@Service
public class VerificationCodeService {
    @Autowired
    private ServiceVerificationcodeClient serviceVerificationcodeClient;

    private String verificationCodePrefix = "passenger-verification-code-";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private String generatorKeyByPhone(String passengerPhone){
        return verificationCodePrefix + passengerPhone;
    }

    public ResponseResult generatorCode(String passengerPhone){
        // call verification service, get code
        System.out.println("call verification code service: ");

        ResponseResult<NumberCodeResponse> numberCodeResponse = serviceVerificationcodeClient.getNumberCode(6);
        int numberCode = numberCodeResponse.getData().getNumberCode();
        System.out.println("api-passenger receives code: "+numberCode);

        // Key,value,expiration time
        //String key = verificationCodePrefix + passengerPhone;
        String key = generatorKeyByPhone(passengerPhone);
        // store code in redis
        System.out.println("store in redis...");
        stringRedisTemplate.opsForValue().set(key,numberCode+"",2, TimeUnit.MINUTES);


        return ResponseResult.success("");
    }

    public ResponseResult checkCode(String passengerPhone, String verificationCode){
        // 1.Retrieve code from redis given phone number
        System.out.println("read verification code from redis based on phone number..");
        // 1.1 Generate key
        String key = generatorKeyByPhone(passengerPhone);

        // 1.2 Retrieve value with key
        String codeRedis = stringRedisTemplate.opsForValue().get(key);
        System.out.println("value with key " + passengerPhone + "has the value of "+ codeRedis);

        TokenResponse tokenResponse = new TokenResponse();
        //tokenResponse.setToken("token value...");

        // verify code
        System.out.println("Check verification code");
        if(StringUtils.isBlank(codeRedis)){
            tokenResponse.setToken("verification code not exist..");
            return ResponseResult.notExistError(tokenResponse);
        }
        if(!verificationCode.trim().equals(codeRedis.trim())){
            tokenResponse.setToken("verification code incorrect..");
            return ResponseResult.notMatchError(tokenResponse);
        }

        // Check if the user exists in the db, process accordingly
        System.out.println("Check if user exists, process accordingly.. ");

        // Issue token
        System.out.println("issue tokens.. ");

        //TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken("token value...");

        return ResponseResult.success(tokenResponse);
    }
}
