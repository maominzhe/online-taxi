package com.mashibing.apipassenger.remote;

import com.mashibing.internalcommon.dto.ResponseResult;
import com.mashibing.internalcommon.request.OrderRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.awt.image.RescaleOp;

/**
 * @Auther: Minzhe Mao
 * @Date: 07.08.23 -08 - 07
 * @Description: com.mashibing.apipassenger.remote
 * @Version: 1.0
 **/
@FeignClient("service-order")
public interface ServiceOrderClient {
    @RequestMapping(method = RequestMethod.POST, value = "/order/add")
    public ResponseResult add(@RequestBody OrderRequest orderRequest);
}
