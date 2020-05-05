package com.wings.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLb implements MyLoadBalanced{

private AtomicInteger atomicInteger= new AtomicInteger(0);
    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
        int index = this.incrementAndGetModulo()%serviceInstances.size();

        return   serviceInstances.get(index);
    }

    private int incrementAndGetModulo() {
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
//            next = (current + 1) % modulo;
            next =current >=Integer.MAX_VALUE?0:current+1;
        } while(!this.atomicInteger.compareAndSet(current, next));
        System.out.println("***** next" +next);
        return next;
    }
}
