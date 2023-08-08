package com.mashibing.servicemap.service;

import com.mashibing.internalcommon.dto.ResponseResult;
import com.mashibing.servicemap.remote.TrackClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: Minzhe Mao
 * @Date: 08.08.23 -08 - 08
 * @Description: com.mashibing.servicemap.service
 * @Version: 1.0
 **/
@Service
public class TrackService {
    @Autowired
    TrackClient trackClient;
    public ResponseResult add(String tid){
        return trackClient.add(tid);
    }
}
