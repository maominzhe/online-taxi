package com.mashibing.apipassenger.controller;

import com.mashibing.apipassenger.service.OrderService;
import com.mashibing.internalcommon.dto.ResponseResult;
import com.mashibing.internalcommon.request.OrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: Minzhe Mao
 * @Date: 06.08.23 -08 - 06
 * @Description: com.mashibing.apipassenger.controller
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {
    //create order/booking
    @Autowired
    OrderService oderService;
    @PostMapping("/add")
    public ResponseResult add(@RequestBody OrderRequest orderRequest){
        //System.out.println(orderRequest);
        return oderService.add(orderRequest);
    }
}
