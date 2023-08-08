package com.mashibing.servicemap.controller;

import com.mashibing.internalcommon.dto.ResponseResult;
import com.mashibing.servicemap.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Minzhe Mao
 * @Date: 08.08.23 -08 - 08
 * @Description: com.mashibing.servicemap.controller
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/track")
public class TrackController {
    @Autowired
    TrackService trackService;

    @PostMapping("/add")
    public ResponseResult add(String tid){
        return trackService.add(tid);
    }
}
