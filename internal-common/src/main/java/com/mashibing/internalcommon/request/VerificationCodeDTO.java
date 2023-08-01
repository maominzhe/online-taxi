package com.mashibing.internalcommon.request;

import lombok.Data;

/**
 * @Auther: Minzhe Mao
 * @Date: 01.08.23 -08 - 01
 * @Description: com.mashibing.internalcommon.request
 * @Version: 1.0
 **/
@Data
public class VerificationCodeDTO {
    private String passengerPhone;
    private String verificationCode;
}
