package com.mashibing.internalcommon.request;

import lombok.Data;

/**
 * @Auther: Minzhe Mao
 * @Date: 08.08.23 -08 - 08
 * @Description: com.mashibing.internalcommon.request
 * @Version: 1.0
 **/
@Data
public class PointRequest {
    private String tid;
    private String trid;
    private PointDTO[] points;
}
