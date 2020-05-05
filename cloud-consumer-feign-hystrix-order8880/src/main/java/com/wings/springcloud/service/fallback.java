package com.wings.springcloud.service;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class fallback implements FallbackFactory<PaymentHystrixService> {
    @Override
    public PaymentHystrixService create(Throwable throwable) {
        return new PaymentHystrixService() {
            @Override
            public String paymentInfo_OK(Integer id) {
                log.error("fallback; reason was:",throwable);
                return "服务报错了";

            }
            @Override
            public String paymentInfo_TimeOut(Integer id) {
                log.error("fallback; reason was:",throwable);
                return "服务报错了";
            }
        };
    }
}
