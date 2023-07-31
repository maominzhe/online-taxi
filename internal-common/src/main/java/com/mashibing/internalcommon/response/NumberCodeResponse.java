package com.mashibing.internalcommon.response;

import lombok.Data;

/**
 * @Auther: Minzhe Mao
 * @Date: 31.07.23 -07 - 31
 * @Description: com.mashibing.internalcommon.response
 * @Version: 1.0
 **/
@Data
public class NumberCodeResponse {
    private int numberCode;

    public int getNumberCode() {
        return numberCode;
    }

    public void setNumberCode(int numberCode) {
        this.numberCode = numberCode;
    }
}
