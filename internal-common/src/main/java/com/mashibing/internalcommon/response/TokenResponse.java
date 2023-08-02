package com.mashibing.internalcommon.response;

import lombok.Data;

/**
 * @Auther: Minzhe Mao
 * @Date: 01.08.23 -08 - 01
 * @Description: com.mashibing.internalcommon.response
 * @Version: 1.0
 **/
@Data
public class TokenResponse {
    private String refreshToken;

    private String accessToken;

}
