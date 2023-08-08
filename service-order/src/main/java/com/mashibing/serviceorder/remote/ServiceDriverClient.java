package com.mashibing.serviceorder.remote;

import com.mashibing.internalcommon.dto.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Auther: Minzhe Mao
 * @Date: 08.08.23 -08 - 08
 * @Description: com.mashibing.serviceorder.remote
 * @Version: 1.0
 **/
@FeignClient("service-driver-user")
public interface ServiceDriverClient {

    @RequestMapping(method = RequestMethod.GET, value = "/get-driver")
    public ResponseResult getDriverByCarId(@RequestBody Long carId);
}
