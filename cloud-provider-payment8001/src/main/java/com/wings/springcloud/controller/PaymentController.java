package com.wings.springcloud.controller;

import com.wings.springcloud.entities.CommonResult;
import com.wings.springcloud.entities.Payment;
import com.wings.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    //只传给前端CommonResult，不需要前端了解其他的组件
    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("*****插入结果："+result);
        if(result > 0){
            return new CommonResult(200,"插入数据成功"+"serverPort -->"+serverPort,result);
        }else{
            return new CommonResult(444,"插入数据失败"+"serverPort -->"+serverPort,null);
        }
    }
    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("*****查询结果："+payment);
        if(payment != null){
                return new CommonResult(200,"查询成功"+"serverPort -->"+serverPort,payment);
        }else{
            return new CommonResult(444,"没有对应记录,查询ID："+"serverPort -->"+serverPort+id,null);
        }
    }

    @GetMapping(value = "/payment/get/lb")
    public CommonResult getLb(){
        return new CommonResult(200,"server port:",serverPort);
    }

    @GetMapping(value = "/payment/lb")
    public CommonResult lb(){
        return new CommonResult(200,"server port:",serverPort);
    }
    @GetMapping(value = "/payment/feign/timout")
    public CommonResult getPaymentFeignTimeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new CommonResult(200,"server port:",serverPort);
    }

}
