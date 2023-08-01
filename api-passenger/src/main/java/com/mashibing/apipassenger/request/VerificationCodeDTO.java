package com.mashibing.apipassenger.request;

import lombok.Data;

/**
 * @Auther: Minzhe Mao
 * @Date: 30.07.23 -07 - 30
 * @Description: com.mashibing.apipassenger.request
 * @Version: 1.0
 **/
@Data
public class VerificationCodeDTO {
    private String passengerPhone;

    private String verificationCode;

}
