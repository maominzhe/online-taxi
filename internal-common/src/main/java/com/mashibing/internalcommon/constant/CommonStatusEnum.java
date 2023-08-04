package com.mashibing.internalcommon.constant;

/**
 * @Auther: Minzhe Mao
 * @Date: 30.07.23 -07 - 30
 * @Description: com.mashibing.internalcommon.constant
 * @Version: 1.0
 **/
public enum CommonStatusEnum {
    SUCCESS(1,"success"),
    FAIL(0,"fail"),
    /**
     * verification code error: 1000-1099
     */
    VERIFICATION_CODE_NOT_EXIST_ERROR(1099,"verification code does not exist.."),
    VERIFICATION_CODE_ERROR(1000,"verification code is not correct.."),
    /**
     * token code error: 1100-1199
     */
    TOKEN_ERROR(1199,"invalid token.."),
    /**
     * USER ERROR: 1200-1299
     */
    USER_NOT_EXISTS(1200,"user not exists..")
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
