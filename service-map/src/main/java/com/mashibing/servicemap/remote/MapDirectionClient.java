package com.mashibing.servicemap.remote;

import com.mashibing.internalcommon.constant.AmapConfigConstants;
import com.mashibing.internalcommon.dto.ResponseResult;
import com.mashibing.internalcommon.response.DirectionResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Auther: Minzhe Mao
 * @Date: 06.08.23 -08 - 06
 * @Description: com.mashibing.servicemap.remote
 * @Version: 1.0
 **/
@Slf4j
@Service
public class MapDirectionClient {
    @Value("${amap.key}")
    private String amapKey;


    @Autowired
    private RestTemplate restTemplate;

    // third party API for journey route and duration
    public DirectionResponse direction(String depLongitude, String depLatitude, String desLongitude, String desLatitude){
        // Assembly request call url



        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(AmapConfigConstants.DIRECTION_URL);
        urlBuilder.append("?");
        urlBuilder.append("origin=").append(depLongitude).append(",").append(depLatitude);
        urlBuilder.append("&");
        urlBuilder.append("destination=").append(desLongitude).append(",").append(desLatitude);
        urlBuilder.append("&");
        urlBuilder.append("extensions=base");
        urlBuilder.append("&");
        urlBuilder.append("output=json");
        urlBuilder.append("&");
        urlBuilder.append("key=").append(amapKey);


        log.info(urlBuilder.toString());

        // call map api
        log.info("calling map api for route planning: " + urlBuilder.toString());
        ResponseEntity<String> directionEntity = restTemplate.getForEntity(urlBuilder.toString(), String.class);
        String directionString = directionEntity.getBody();
        log.info("map API for routing planning, returning response: " + directionString);

        // parse response
        return parseDirectionEntity(directionString);
    }

    private DirectionResponse parseDirectionEntity(String directionString){
        DirectionResponse directionResponse = null;

        JSONObject result = JSONObject.fromObject(directionString);

        try {
            if(result.has(AmapConfigConstants.STATUS)) {
                int status = result.getInt(AmapConfigConstants.STATUS);
                if(status==1){
                    if(result.has(AmapConfigConstants.ROUTE)){
                        JSONObject routeObject = result.getJSONObject(AmapConfigConstants.ROUTE);
                        JSONArray pathsArray = routeObject.getJSONArray(AmapConfigConstants.PATHS);
                        JSONObject pathObject = pathsArray.getJSONObject(0);        // return first path
                        directionResponse = new DirectionResponse();

                        if(pathObject.has(AmapConfigConstants.DISTANCE)){
                            int distance = pathObject.getInt(AmapConfigConstants.DISTANCE);
                            directionResponse.setDistance(distance);
                        }
                        if(pathObject.has(AmapConfigConstants.DURATION)){
                            int duration = pathObject.getInt(AmapConfigConstants.DURATION);
                            directionResponse.setDuration(duration);
                        }
                    }
                }

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return directionResponse;
    }
}
