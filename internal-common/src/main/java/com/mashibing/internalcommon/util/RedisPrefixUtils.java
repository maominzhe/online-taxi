package com.mashibing.internalcommon.util;

/**
 * @Auther: Minzhe Mao
 * @Date: 02.08.23 -08 - 02
 * @Description: com.mashibing.internalcommon.util
 * @Version: 1.0
 **/
public class RedisPrefixUtils {
    public static final String verificationcodePrefix = "verification-code-";

    public static final String tokenPrefix = "token-";

    //generate key with phone number
    public static String generatorKeyByPhone(String phone, String identity){
        return verificationcodePrefix + identity + "-" + phone;
    }

    public static String generatorTokenKey(String phone, String identity, String tokenType){
        return tokenPrefix + phone + "-" + identity + "-" + tokenType;
    }
}
