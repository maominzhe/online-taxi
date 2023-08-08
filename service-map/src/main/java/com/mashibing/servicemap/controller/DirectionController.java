package com.mashibing.servicemap.controller;

import com.mashibing.internalcommon.dto.ResponseResult;
import com.mashibing.internalcommon.request.ForecastDTO;
import com.mashibing.servicemap.service.DirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: Minzhe Mao
 * @Date: 05.08.23 -08 - 05
 * @Description: com.mashibing.servicemap.controller
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/direction")
public class DirectionController {
    @Autowired
    private DirectionService directionService;
    @PostMapping("/driving")
    public ResponseResult driving(@RequestBody ForecastDTO forecastDTO){

        String depLongitude = forecastDTO.getDepLongitude();
        String depLatitude = forecastDTO.getDepLatitude();
        String destLongitude = forecastDTO.getDestLongitude();
        String destLatitude = forecastDTO.getDestLatitude();

        return directionService.driving(depLongitude, depLatitude, destLongitude, destLatitude);
    }

    @GetMapping("/driving-routing")
    public ResponseResult routing(@RequestBody ForecastDTO forecastDTO){

        String depLongitude = forecastDTO.getDepLongitude();
        String depLatitude = forecastDTO.getDepLatitude();
        String destLongitude = forecastDTO.getDestLongitude();
        String destLatitude = forecastDTO.getDestLatitude();

        return directionService.driving(depLongitude, depLatitude, destLongitude, destLatitude);
    }
}
