package com.xule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Package com.xule
 * @author: xule
 * @date: 2020/7/23 14:51
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Oauth2AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2AuthApplication.class,args);
    }
}
