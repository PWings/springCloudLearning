package com.wings.springcloud.service;


import com.wings.springcloud.entities.CommonResult;
import com.wings.springcloud.entities.Payment;
import config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE",configuration = FeignConfig.class)  //指定调用哪个微服务
public interface PaymentFeignService {

    @GetMapping(value = "/payment/get/{id}")    //哪个地址
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    /**
     * feign 客户端超时调用
     * @return
     */
    @GetMapping(value = "/payment/feign/timout")
    String getPaymentFeignTimeout();
}
