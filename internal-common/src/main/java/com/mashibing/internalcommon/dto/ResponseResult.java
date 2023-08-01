package com.mashibing.internalcommon.dto;

import com.mashibing.internalcommon.constant.CommonStatusEnum;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Auther: Minzhe Mao
 * @Date: 30.07.23 -07 - 30
 * @Description: com.mashibing.internalcommon.dto
 * @Version: 1.0
 **/
@Data
@Accessors(chain = true)
public class ResponseResult<T> {

    private int code;
    private String message;
    private T data;
    //private String token;

    public static <T> ResponseResult success(){
        return new ResponseResult().setCode(CommonStatusEnum.SUCCESS.getCode()).
                setMessage(CommonStatusEnum.SUCCESS.getValue());
    }

    public static <T> ResponseResult success(T data){
        return new ResponseResult().setCode(CommonStatusEnum.SUCCESS.getCode())
                .setMessage(CommonStatusEnum.SUCCESS.getValue()).setData(data);

    }

    public static ResponseResult fail(int code, String message){
        return new ResponseResult().setCode(code).setMessage(message);
    }

    public static ResponseResult fail(int code, String message, String data){
        return new ResponseResult().setCode(code).setMessage(message).setData(data);
    }

    public static <T> ResponseResult fail(T data){
        return new ResponseResult().setData(data);
    }

    public static <T> ResponseResult notExistError(T data){
        return new ResponseResult().setCode(CommonStatusEnum.VERIFICATION_CODE_NOT_EXIST_ERROR.getCode()).
                setMessage(CommonStatusEnum.VERIFICATION_CODE_NOT_EXIST_ERROR.getValue()).setData(data);
    }
    public static ResponseResult notExistError(){
        return new ResponseResult().setData(CommonStatusEnum.VERIFICATION_CODE_NOT_EXIST_ERROR.getCode()).setMessage(CommonStatusEnum.VERIFICATION_CODE_NOT_EXIST_ERROR.getValue());
    }

    public static <T> ResponseResult notMatchError(T data){
        return new ResponseResult().setCode(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode()).setMessage(CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue()).setData(data);
    }

    public static ResponseResult notMatchError(){
        return new ResponseResult().setCode(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode()).setMessage(CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
    }
}
