package com.xule.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package com.mhc.controller
 * @author: xule
 * @date: 2020/7/23 16:53
 */
@RestController
public class HelloController {


    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }
}
