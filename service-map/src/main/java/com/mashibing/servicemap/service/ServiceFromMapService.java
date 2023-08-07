package com.mashibing.servicemap.service;

import com.mashibing.internalcommon.dto.ResponseResult;
import com.mashibing.servicemap.remote.ServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: Minzhe Mao
 * @Date: 07.08.23 -08 - 07
 * @Description: com.mashibing.servicemap.service
 * @Version: 1.0
 **/
@Service
public class ServiceFromMapService {
    @Autowired
    ServiceClient serviceClient;

    public ResponseResult add(String name){

        return serviceClient.add(name);
    }

}
