package com.mashibing.internalcommon.constant;

/**
 * @Auther: Minzhe Mao
 * @Date: 30.07.23 -07 - 30
 * @Description: com.mashibing.internalcommon.constant
 * @Version: 1.0
 **/
public enum CommonStatusEnum {
    SUCCESS(1,"success"),
    FAIL(0,"fail")
    ;
    private int code;
    private String value;

    CommonStatusEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
