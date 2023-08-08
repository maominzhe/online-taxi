package com.mashibing.internalcommon.request;

import lombok.Data;

/**
 * @Auther: Minzhe Mao
 * @Date: 05.08.23 -08 - 05
 * @Description: com.mashibing.internalcommon.request
 * @Version: 1.0
 **/
@Data
public class ForecastDTO {
    private String depLongitude;
    private String depLatitude;
    private String destLongitude;
    private String destLatitude;
//    private String cityCode;
//    private String vehicleType;
}
