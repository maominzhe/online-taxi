package com.mashibing.serviceorder.remote;

import com.mashibing.internalcommon.dto.DriverUser;
import com.mashibing.internalcommon.dto.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * @Auther: Minzhe Mao
 * @Date: 08.08.23 -08 - 08
 * @Description: com.mashibing.serviceorder.remote
 * @Version: 1.0
 **/
@FeignClient("api-driver")
public interface ServiceDriverUserClient {
    @GetMapping("/get-driver")
    public ResponseResult<DriverUser> getDriverByCarId(@RequestParam Long carId);
}
