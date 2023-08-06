package com.mashibing.servicemap.service;

import com.mashibing.internalcommon.dto.ResponseResult;
import com.mashibing.internalcommon.response.DirectionResponse;
import com.mashibing.servicemap.remote.MapDirectionClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: Minzhe Mao
 * @Date: 05.08.23 -08 - 05
 * @Description: com.mashibing.servicemap.service
 * @Version: 1.0
 **/
@Service
public class DirectionService {
    @Autowired
    private MapDirectionClient mapDirectionClient;
    /**
     * Get the distance and duration of the trip based on the latitude and longitude of the start and
     * end points, and the return value is in meters and minutes.
     * @param depLongitude
     * @param depLatitude
     * @param desLongitude
     * @param desLatitude
     * @return
     */


    public ResponseResult driving(String depLongitude, String depLatitude, String desLongitude, String desLatitude){
        // call third party api for journey duration and route
        return ResponseResult.success(mapDirectionClient.direction(depLongitude, depLatitude, desLongitude, desLatitude));
    }
}
