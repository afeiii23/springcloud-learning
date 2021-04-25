package com.xule.feign.consumer.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.xule.feign.consumer.RemoteClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Package com.xule.feign.consumer.controller
 * @author: xule
 * @date: 2020/7/24 17:44
 */
@RestController
public class NacosController {

    @Autowired
    private RemoteClient client;

    @GetMapping("/hello")
    @HystrixCommand(fallbackMethod = "helloFallBack", threadPoolKey = "hello",commandProperties = {},threadPoolProperties = {@HystrixProperty(name = "coreSize", value = "2"), @HystrixProperty(name = "maxQueueSize", value = "1")})
    public String hello() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        return "9989" + client.hello();
    }

    public String helloFallBack() {
        return "限流啦";
    }
}
