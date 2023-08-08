package com.mashibing.servicemap.service;

import com.mashibing.internalcommon.dto.ResponseResult;
import com.mashibing.internalcommon.response.TerminalResponse;
import com.mashibing.servicemap.remote.TerminalClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Response;
import java.util.List;

/**
 * @Auther: Minzhe Mao
 * @Date: 08.08.23 -08 - 08
 * @Description: com.mashibing.servicemap.service
 * @Version: 1.0
 **/
@Service
@Slf4j
public class TerminalService {
    @Autowired
    TerminalClient terminalClient;
    public ResponseResult add(String name, String desc){
        return terminalClient.add(name, desc);
    }

    /**
     *
     * @param center    user departure location
     * @param radius    search radius(meter)
     * @return
     */
    public ResponseResult<List<TerminalResponse>> aroundsearch(String center, Integer radius){
        return terminalClient.aroundsearch(center,radius);
    }

}
