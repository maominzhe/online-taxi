package com.mashibing.apipassenger.service;

import com.mashibing.apipassenger.remote.ServiceVerificationcodeClient;
import com.mashibing.internalcommon.dto.ResponseResult;
import com.mashibing.internalcommon.response.NumberCodeResponse;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: Minzhe Mao
 * @Date: 30.07.23 -07 - 30
 * @Description: com.mashibing.apipassenger.service
 * @Version: 1.0
 **/
@Service
public class VerificationCodeService {
    @Autowired
    public ServiceVerificationcodeClient serviceVerificationcodeClient;
    public String generatorCode(String passengerPhone){
        // call verification service, get code
        System.out.println("call verification code service: ");

        ResponseResult<NumberCodeResponse> numberCodeResponse = serviceVerificationcodeClient.getNumberCode(6);
        int numberCode = numberCodeResponse.getData().getNumberCode();
        System.out.println("api-passenger receives code: "+numberCode);

        // store code in redis
        System.out.println("store in redis...");

        // return json
        JSONObject result = new JSONObject();
        result.put("code",1);
        result.put("message","success");
        return result.toString();
    }
}
