package com.mashibing.apipassenger.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.mashibing.internalcommon.constant.TokenConstants;
import com.mashibing.internalcommon.dto.ResponseResult;
import com.mashibing.internalcommon.dto.TokenResult;
import com.mashibing.internalcommon.util.JwtUtils;
import com.mashibing.internalcommon.util.RedisPrefixUtils;
import jdk.nashorn.internal.parser.Token;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @Auther: Minzhe Mao
 * @Date: 02.08.23 -08 - 02
 * @Description: com.mashibing.apipassenger.interceptor
 * @Version: 1.0
 **/

public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    @Qualifier("stringRedisTemplate")
    private StringRedisTemplate stringRedisTemplate;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        boolean result = true;
        String resultString = "";
        String token = request.getHeader("Authorization");

        TokenResult tokenResult = JwtUtils.checkToken(token);
        if (tokenResult == null){
            resultString = "token invalid..";
            result = false;
        } else {
            String phone = tokenResult.getPhone();
            String identity = tokenResult.getIdentity();
            String tokenKey = RedisPrefixUtils.generatorTokenKey(phone, identity, TokenConstants.ACCESS_TOKEN_TYPE);

            // fetch token from redis with tokenKey.
            //StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
            String tokenRedis = stringRedisTemplate.opsForValue().get(tokenKey);

//            if ((StringUtils.isBlank(tokenRedis)) || (!token.trim().equals(tokenRedis.trim()))){
//                resultString = "invalid token..";
//                result = false;
            }
            if (!result){
//                PrintWriter out = response.getWriter();
//                out.print(JSONObject.fromObject(ResponseResult.fail(resultString)).toString());
                System.out.println("JwtInterceptor: invalid token..");
            }
        return true;
        }


}