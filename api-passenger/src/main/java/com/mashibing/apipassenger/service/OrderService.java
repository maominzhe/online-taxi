package com.mashibing.apipassenger.service;

import com.mashibing.apipassenger.remote.ServiceOrderClient;
import com.mashibing.apipassenger.remote.ServicePassengerUserClient;
import com.mashibing.internalcommon.dto.PassengerUser;
import com.mashibing.internalcommon.dto.ResponseResult;
import com.mashibing.internalcommon.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: Minzhe Mao
 * @Date: 07.08.23 -08 - 07
 * @Description: com.mashibing.apipassenger.service
 * @Version: 1.0
 **/
@Service
public class OrderService {
    @Autowired
    ServiceOrderClient serviceOrderClient;
    @Autowired
    ServicePassengerUserClient servicePassengerUserClient;

    public ResponseResult add(OrderRequest orderRequest){
        //query userId with phoneNumber
        String passengerPhone = orderRequest.getPassengerPhone();
        Long userId = getUserIdByPhone(passengerPhone);
        orderRequest.setPassengerId(userId);
        return serviceOrderClient.add(orderRequest);
    }

    private Long getUserIdByPhone(String phone){
        ResponseResult<PassengerUser> userByPhone = servicePassengerUserClient.getUserByPhone(phone);
        return userByPhone.getData().getId();
    }
}
