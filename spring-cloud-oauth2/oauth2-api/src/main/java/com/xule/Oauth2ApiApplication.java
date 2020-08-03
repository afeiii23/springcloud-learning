package com.xule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Package com.xule
 * @author: xule
 * @date: 2020/7/24 10:26
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Oauth2ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2ApiApplication.class,args);
    }
}
