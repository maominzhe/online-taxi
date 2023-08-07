package com.mashibing.serviceorder.service;

import com.mashibing.internalcommon.dto.OrderInfo;
import com.mashibing.internalcommon.dto.ResponseResult;
import com.mashibing.internalcommon.request.OrderRequest;
import com.mashibing.serviceorder.mapper.OrderInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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

    public ResponseResult add(OrderRequest orderRequest){

        // create new order from passenger
        OrderInfo orderInfo = new OrderInfo();

        // copy variables from request body
        BeanUtils.copyProperties(orderRequest, orderInfo);

        // complete time data
        LocalDateTime now = LocalDateTime.now();
        orderInfo.setGmtCreate(now);
        orderInfo.setGmtModified(now);

        // insert into database
        orderInfoMapper.insert(orderInfo);
        return ResponseResult.success("");

    }
}
