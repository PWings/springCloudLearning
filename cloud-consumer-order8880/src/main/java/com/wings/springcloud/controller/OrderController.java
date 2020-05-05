package com.wings.springcloud.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.wings.springcloud.entities.CommonResult;
import com.wings.springcloud.entities.Payment;
import com.wings.springcloud.lb.MyLoadBalanced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
public class OrderController {
    private static final String PAYMENT_URL="http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @Autowired
    private MyLoadBalanced  myLoadBalanced;

    @Resource
    private DiscoveryClient discoveryClient;


    
    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }
    @GetMapping("/consumer/payment/get/lb")
    public String getPaymentLb(){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if(CollectionUtil.isEmpty(instances)){
            return null;
        }
        ServiceInstance instance = myLoadBalanced.instance(instances);
        URI uri = instance.getUri();
       return restTemplate.getForObject(uri+"/payment/get/lb",String.class);
    }

}
