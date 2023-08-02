package com.mashibing.apipassenger.interceptor;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Auther: Minzhe Mao
 * @Date: 02.08.23 -08 - 02
 * @Description: com.mashibing.apipassenger.interceptor
 * @Version: 1.0
 **/
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor())

        // intercept path..
                .addPathPatterns("/**")

        // white list
                .excludePathPatterns("/noauthTest")
                .excludePathPatterns("/verification-code-check")
                .excludePathPatterns("/verification-code");
    }
}
