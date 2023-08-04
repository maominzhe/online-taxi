package com.mashibing.apipassenger.controller;

import com.mashibing.apipassenger.service.UserService;
import com.mashibing.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: Minzhe Mao
 * @Date: 04.08.23 -08 - 04
 * @Description: com.mashibing.apipassenger.controller
 * @Version: 1.0
 **/
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @GetMapping("/users")
    public ResponseResult getUser(HttpServletRequest request){

        // get accessToken from http header
        String accessToken = request.getHeader("Authorization");


        // query redis based on token for user info
        String userInfo = stringRedisTemplate.opsForValue().get(accessToken);

        return userService.getUserByAccessToken(accessToken);

    }
}
