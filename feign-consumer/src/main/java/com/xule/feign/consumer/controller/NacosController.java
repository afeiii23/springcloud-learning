package com.xule.feign.consumer.controller;

import com.xule.feign.consumer.RemoteClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public String hello(){
        return "9989" + client.hello();
    }
}
