package com.mashibing.servicemap.remote;

import com.mashibing.internalcommon.constant.AmapConfigConstants;
import com.mashibing.internalcommon.dto.ResponseResult;
import com.mashibing.internalcommon.request.PointDTO;
import com.mashibing.internalcommon.request.PointRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * @Auther: Minzhe Mao
 * @Date: 08.08.23 -08 - 08
 * @Description: com.mashibing.servicemap.remote
 * @Version: 1.0
 **/
@Service
public class PointClient {
    @Value("${amap.key}")
    private String amapKey;

    @Value("${amap.sid}")
    private String amapSid;

    @Autowired
    private RestTemplate restTemplate;

    public ResponseResult upload (PointRequest pointRequest){
        // &key=<用户的key>
        // 拼装请求的url
        StringBuilder url = new StringBuilder();
        url.append(AmapConfigConstants.POINT_UPLOAD);
        url.append("?");
        url.append("key=").append(amapKey);
        url.append("&");
        url.append("sid=").append(amapSid);
        url.append("&");
        url.append("tid=").append(pointRequest.getTid());
        url.append("&");
        url.append("trid=").append(pointRequest.getTrid());
        url.append("&");
        url.append("points=");
        PointDTO[] points = pointRequest.getPoints();
        url.append("%5B");
        for (PointDTO p : points
        ) {
            url.append("%7B");
            String locatetime = p.getLocatetime();
            String location = p.getLocation();
            url.append("%22location%22");
            url.append("%3A");
            url.append("%22").append(location).append("%22");
            url.append("%2C");

            url.append("%22locatetime%22");
            url.append("%3A");
            url.append(locatetime);

            url.append("%7D");
        }
        url.append("%5D");

        System.out.println("上传位置请求："+url.toString());
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(URI.create(url.toString()), null, String.class);
        System.out.println("上传位置响应："+stringResponseEntity.getBody());

        return ResponseResult.success();
    }

}
