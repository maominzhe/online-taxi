package com.mashibing.serviceorder.service;

import com.mashibing.internalcommon.constant.OrderConstants;
import com.mashibing.internalcommon.dto.*;
import com.mashibing.internalcommon.request.ForecastDTO;
import com.mashibing.internalcommon.request.OrderRequest;
import com.mashibing.internalcommon.response.DirectionResponse;
import com.mashibing.internalcommon.response.TerminalResponse;
import com.mashibing.serviceorder.mapper.DriverUserMapper;
import com.mashibing.serviceorder.mapper.OrderInfoMapper;
import com.mashibing.serviceorder.remote.ServiceDriverClient;
import com.mashibing.serviceorder.remote.ServiceMapClient;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Minzhe Mao
 * @Date: 07.08.23 -08 - 07
 * @Description: com.mashibing.serviceorder.service
 * @Version: 1.0
 **/
@Service
@Slf4j
public class OrderInfoService {
    @Autowired
    OrderInfoMapper orderInfoMapper;

    @Autowired
    ServiceMapClient serviceMapClient;

    @Autowired
    DriverUserMapper driverUserMapper;

    @Autowired
    ServiceDriverClient serviceDriverClient;


    public ResponseResult add(OrderRequest orderRequest){

        // create new order from passenger
        OrderInfo orderInfo = new OrderInfo();

        // copy variables from request body
        BeanUtils.copyProperties(orderRequest, orderInfo);

        // complete time data
        LocalDateTime now = LocalDateTime.now();
        orderInfo.setGmtCreate(now);
        orderInfo.setGmtModified(now);

        // calculate journey duration
        ForecastDTO forecastDTO = new ForecastDTO();
        forecastDTO.setDepLongitude(orderInfo.getDepLongitude());
        forecastDTO.setDepLatitude(orderInfo.getDepLatitude());
        forecastDTO.setDesLongitude(orderInfo.getDestLongitude());
        forecastDTO.setDesLatitude(orderInfo.getDestLatitude());
//
//        ResponseResult<DirectionResponse> direction = serviceMapClient.driving(forecastDTO);
//        Integer distance = direction.getData().getDistance();
//        Integer duration = direction.getData().getDuration();

        //DirectionResponse directionResponse = serviceMapClient.driving(forecastDTO).getData();

        //ResponseResult directionResponse = getEstimatedDuration(forecastDTO);

        //ResponseResult directionResponse = serviceMapClient.driving(forecastDTO);

        //orderInfo.setJourneyDistance(directionResponse.getDistance());
        //orderInfo.setJourneyDuration(directionResponse.getDuration());

        // insert into database
        orderInfoMapper.insert(orderInfo);

        for (int i=0; i<6; i++){
            //dispatch real time order
            int result = dispatchRealTimeOrder(orderInfo);
            if(result==1){
                break;
            }else{
                try{
                    Thread.sleep(2);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }


        return ResponseResult.success(orderInfo);

    }



    public int dispatchRealTimeOrder(OrderInfo orderInfo){


//        //test
//        DriverUser driverUser = new DriverUser();
//        driverUser = getDriverByCarId(1584359540577861633L).getData();



        log.info("orderInfoService.dispatchRealTimeOrder:..");
        int result = 0;

        //radius = search radius for 2,4,5 km.
        String depLatitude = orderInfo.getDepLatitude();
        String depLongitude = orderInfo.getDepLongitude();
        String center = depLatitude+","+depLongitude;

        List<Integer> radiusList = new ArrayList<>();
        radiusList.add(2000);
        radiusList.add(4000);
        radiusList.add(5000);

        // initialize search result
        ResponseResult<List<TerminalResponse>> listResponseResult = null;

        for (int i=0; i<radiusList.size();i++){
            Integer radius = radiusList.get(i);
            listResponseResult = serviceMapClient.terminalAroundSearch(center, radius);

            // calculate journey duration
            ForecastDTO forecastDTO = new ForecastDTO();
            forecastDTO.setDepLongitude(orderInfo.getDepLongitude());
            forecastDTO.setDepLatitude(orderInfo.getDepLatitude());
            forecastDTO.setDesLongitude(orderInfo.getDestLongitude());
            forecastDTO.setDesLatitude(orderInfo.getDestLatitude());
//
            ResponseResult<DirectionResponse> direction = serviceMapClient.driving(forecastDTO);
            Integer distance = direction.getData().getDistance();
            Integer duration = direction.getData().getDuration();

            log.info("in the range of "+radius + "m, found cars: "+ JSONArray.fromObject(listResponseResult.getData()).toString());

            //parse terminal response
            List<TerminalResponse> data = listResponseResult.getData();
            search:
            for (int j=0;j<data.size();j++){
                TerminalResponse terminalResponse = data.get(j);
                Long carId = terminalResponse.getCarId();

                // get car geo status
                String longitude = terminalResponse.getLongitude();
                String latitude = terminalResponse.getLatitude();
                log.info("found carId: " + carId + ", with geo status: " + longitude + "," + latitude);

                // get driver info
                //DriverUser driverUser = getDriverUserByCarId(carId);
                DriverUser driverUser = new DriverUser();
                Long driverId = driverUser.getId();
                String driverPhone = driverUser.getDriverPhone();
                String driverName = driverUser.getDriverName();

                if (getDriverWorkingStatus(driverId)==0){
                    // found driver is not available
                    log.info("carId: "+carId+"is available");
                    continue;
                }
                // calculate pick up route and duration
                ForecastDTO forecastDTO1 = new ForecastDTO();
                forecastDTO.setDepLongitude(longitude);
                forecastDTO.setDepLatitude(latitude);
                String passengerLongitude = orderInfo.getDepLongitude();
                String passengerLatitude = orderInfo.getDepLatitude();
                forecastDTO.setDesLatitude(passengerLatitude);
                forecastDTO.setDesLongitude(passengerLongitude);
                //DirectionResponse directionResponse = serviceMapClient.driving(forecastDTO);

                // to pick up distance
//                Integer driverPassengerDistance = directionResponse.getDistance();
//                Integer driverPickupTime = directionResponse.getDuration();

                // estimated






                // found available driver, match driver and info to order.
                orderInfo.setDriverId(driverId);
                orderInfo.setDriverPhone(driverPhone);
                orderInfo.setDriverName(driverName);
                orderInfo.setReceiveOrderCarLongitude(longitude);
                orderInfo.setReceiveOrderCarLatitude(latitude);

                orderInfo.setReceiveOrderTime(LocalDateTime.now());
                orderInfo.setOrderStatus(OrderConstants.DRIVER_RECEIVE_ORDER);

                orderInfoMapper.updateById(orderInfo);


                // notify passenger
                //TODO


                result = 1;
                // found driver， exit search loop
                break search;
            }
        }
        return result;
    }


//    public ResponseResult<DirectionResponse> getEstimatedDuration(ForecastDTO forecastDTO){
//
//        return serviceMapClient.driving(forecastDTO);
//    }



    private Integer getDriverWorkingStatus(Long driverId){
        // mock 0:unavailable 1:available
        return 1;
    }


    private ResponseResult<DriverUser> getDriverByCarId(Long CarId){
        return serviceDriverClient.getDriverByCarId(CarId);
    }
}