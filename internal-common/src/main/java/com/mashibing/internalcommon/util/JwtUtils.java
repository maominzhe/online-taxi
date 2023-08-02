package com.mashibing.internalcommon.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mashibing.internalcommon.dto.TokenResult;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Minzhe Mao
 * @Date: 02.08.23 -08 - 02
 * @Description: com.mashibing.internalcommon.util
 * @Version: 1.0
 **/
public class JwtUtils {
    private static final String SIGN ="imasignature";
    private static final String JWT_KEY_PHONE = "phone";

    private static final String JWT_KEY_IDENTITY = "identity";

    // generate token
    private static String generatorToken(String passengerPhone, String identity){
        Map<String,String> map = new HashMap<>();
        map.put(JWT_KEY_PHONE, passengerPhone);
        map.put(JWT_KEY_IDENTITY, identity);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        Date date = calendar.getTime();

        JWTCreator.Builder builder = JWT.create();

        map.forEach(
                (k,v) -> {
            builder.withClaim(k,v);
            }
        );

        builder.withExpiresAt(date);


        // generate token
        String sign = builder.sign(Algorithm.HMAC256(SIGN));

        return sign;
    }

    // decode token
//    public static DecodedJWT parseToken(String token){
//        return JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
//    }

    public static TokenResult parseToken(String token){
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        String phone = verify.getClaim(JWT_KEY_PHONE).toString();
        String identity = verify.getClaim(JWT_KEY_IDENTITY).toString();

        TokenResult tokenResult = new TokenResult();
        tokenResult.setPhone(phone);
        tokenResult.setIdentity(identity);

        return tokenResult;
    }
    public static void main(String[] args) {
//        Map<String, String> map = new HashMap<>();
//        map.put("name","mao");
//        map.put("age","26");
        String s = generatorToken("123456", "1");
        System.out.println("generated token is " + s);

        System.out.println("------decoding token--------");
        System.out.print("decoded token: ");
        System.out.println(parseToken(s));
    }

}
