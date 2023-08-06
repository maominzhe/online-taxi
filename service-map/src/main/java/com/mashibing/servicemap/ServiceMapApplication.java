package com.mashibing.servicemap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @Auther: Minzhe Mao
 * @Date: 05.08.23 -08 - 05
 * @Description: com.mashibing.servicemap
 * @Version: 1.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ServiceMapApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceMapApplication.class);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
