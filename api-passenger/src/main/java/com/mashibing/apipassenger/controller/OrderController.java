package com.mashibing.apipassenger.controller;

import com.mashibing.apipassenger.service.OrderService;
import com.mashibing.internalcommon.dto.ResponseResult;
import com.mashibing.internalcommon.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Minzhe Mao
 * @Date: 06.08.23 -08 - 06
 * @Description: com.mashibing.apipassenger.controller
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/order")
public class OrderController {
    //create order/booking
    @Autowired
    OrderService oderService;
    @PostMapping("/add")
    public ResponseResult add(@RequestBody OrderRequest orderRequest){
        System.out.println(orderRequest);
        return oderService.add(orderRequest);
    }
}
