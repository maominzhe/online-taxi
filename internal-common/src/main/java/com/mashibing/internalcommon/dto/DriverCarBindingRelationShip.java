package com.mashibing.internalcommon.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Auther: Minzhe Mao
 * @Date: 08.08.23 -08 - 08
 * @Description: com.mashibing.internalcommon.dto
 * @Version: 1.0
 **/
@Data
public class DriverCarBindingRelationShip {

    private Long id;

    /**
     * 司机ID
     */
    private Long driverId;

    /**
     * 车辆Id
     */
    private Long carId;

    /**
     * 绑定状态：1：绑定，2：解绑
     */
    private Integer bindState;

    /**
     * 绑定时间
     */
    private LocalDateTime bindingTime;

    /**
     * 解绑时间
     */
    private LocalDateTime unBindingTime;
}
