package com.wings.springcloud.service;

import com.wings.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;
public interface PaymentService {
    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
