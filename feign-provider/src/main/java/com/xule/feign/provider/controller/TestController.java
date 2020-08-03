package com.xule.feign.provider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package com.xule.feign.provider.controller
 * @author: xule
 * @date: 2020/7/24 17:31
 */
@RestController
public class TestController {


    @GetMapping("/hello")
    public String helloNacos() {
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        return "hello nacos 9997";
    }
}
