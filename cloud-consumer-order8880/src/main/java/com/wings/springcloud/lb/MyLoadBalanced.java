package com.wings.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * 自定义轮循负载均衡策略
 */

public interface MyLoadBalanced {

    ServiceInstance   instance(List<ServiceInstance> serviceInstances);
}
