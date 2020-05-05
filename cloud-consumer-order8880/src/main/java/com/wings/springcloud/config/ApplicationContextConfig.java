package com.wings.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {
    @Bean
//    @LoadBalanced    论巡算法 由MyRule 实现
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
