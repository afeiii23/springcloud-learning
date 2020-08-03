package com.xule.feign.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringCloudApplication
@EnableZuulProxy
public class FeignGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignGatewayApplication.class, args);
    }

}
