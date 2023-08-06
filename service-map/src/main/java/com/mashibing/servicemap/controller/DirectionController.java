package com.mashibing.servicemap.controller;

import com.mashibing.internalcommon.dto.ResponseResult;
import com.mashibing.internalcommon.request.ForecastDTO;
import com.mashibing.servicemap.service.DirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/driving")
    public ResponseResult driving(@RequestBody ForecastDTO forecastDTO){

        String depLongitude = forecastDTO.getDepLongitude();
        String depLatitude = forecastDTO.getDepLatitude();
        String desLongitude = forecastDTO.getDesLongitude();
        String desLatitude = forecastDTO.getDesLatitude();

        return directionService.driving(depLongitude, depLatitude, desLongitude, desLatitude);
    }
}
