package com.mashibing.serviceorder.controller;

import com.mashibing.internalcommon.dto.ResponseResult;
import com.mashibing.internalcommon.request.OrderRequest;
import com.mashibing.serviceorder.service.OrderInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Minzhe Mao
 * @Date: 07.08.23 -08 - 07
 * @Description: com.mashibing.serviceorder.controller
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {
    @Autowired
    OrderInfoService orderInfoService;
    @PostMapping("/add")
    public ResponseResult add(@RequestBody OrderRequest orderRequest){
        log.info("service-order"+orderRequest.getAddress());
        return orderInfoService.add(orderRequest);
    }
}
