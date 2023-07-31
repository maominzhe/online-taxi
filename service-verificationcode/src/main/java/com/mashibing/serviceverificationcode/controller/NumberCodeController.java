package com.mashibing.serviceverificationcode.controller;

import com.mashibing.internalcommon.dto.ResponseResult;
import com.mashibing.internalcommon.response.NumberCodeResponse;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Minzhe Mao
 * @Date: 30.07.23 -07 - 30
 * @Description: com.mashibing.serviceverificationcode.controller
 * @Version: 1.0
 **/
@RestController
public class NumberCodeController {
//    @GetMapping("/numberCode/{size}")
//    public String numberCode(@PathVariable("size") int size){
//        System.out.println("size: "+size);
//        double mathRandom = (Math.random()*9 + 1) * (Math.pow(10,size-1));
//        int resultInt = (int)mathRandom;
//
//        NumberCodeResponse numberCodeResponse = new NumberCodeResponse();
//        numberCodeResponse.setNumberCode(resultInt);
//
//
////        JSONObject result = new JSONObject();
////        result.put("code", 1);
////        result.put("message","success");
////        JSONObject data = new JSONObject();
////        data.put("numberCode",resultInt);
////        result.put("data", data);
//        return result.toString();
//    }
    @GetMapping("/numberCode/{size}")
    public ResponseResult numberCode(@PathVariable("size") int size){
        System.out.println("size" + size);
        double mathRandom = (Math.random()*9+1) * (Math.pow(10, size-1));
        int resultInt = (int)mathRandom;
        System.out.println("service-verification generates code:" + resultInt);

        NumberCodeResponse response = new NumberCodeResponse();
        response.setNumberCode(resultInt);

        return ResponseResult.success(response);

    }
}
