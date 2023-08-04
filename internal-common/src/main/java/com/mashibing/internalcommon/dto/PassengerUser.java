package com.mashibing.internalcommon.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Auther: Minzhe Mao
 * @Date: 01.08.23 -08 - 01
 * @Description: com.mashibing.servicepassengeruser.dto
 * @Version: 1.0
 **/
@Data
public class PassengerUser {
    private Long id;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
    private String passengerPhone;
    private String passengerName;
    private byte passengerGender;
    private byte state; //0: valid, 1: invalid
    private String profilePhoto;

}
