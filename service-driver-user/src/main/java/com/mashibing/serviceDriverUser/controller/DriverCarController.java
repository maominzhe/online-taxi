package com.mashibing.serviceDriverUser.controller;

import com.mashibing.internalcommon.dto.DriverUserWorkStatus;
import com.mashibing.internalcommon.dto.ResponseResult;
import com.mashibing.serviceDriverUser.service.DriverCarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Minzhe Mao
 * @Date: 07.08.23 -08 - 07
 * @Description: com.mashibing.serviceDriverUser.controller
 * @Version: 1.0
 **/
@RestController
@Slf4j
public class DriverCarController {
    @Autowired
    private DriverCarService driverCarService;


    //尝试通过这个接口来获取数据库司机信息
    @GetMapping("/check-driver/{driverPhone}")
    public ResponseResult getDriverByPhone(@PathVariable("driverPhone") String driverPhone){
        log.info("service-driver: get driver by phone:" + driverPhone);
        return driverCarService.getDriverByPhone(driverPhone);
    }

    @GetMapping("/get-cat/{carId}")
    public ResponseResult gerCar(@PathVariable("carId") String carId){
        return null;
    }


    @GetMapping("/work-status")
    // get dirving work status
    public ResponseResult<DriverUserWorkStatus> getWorkStatus(Long driverId){
        // test
        Long driverIdMock = 1584359006294835202L;
        //return driverCarService.getWorkStatus(driverIdMock);
        return driverCarService.getWorkStatus(driverIdMock);
    }
}
