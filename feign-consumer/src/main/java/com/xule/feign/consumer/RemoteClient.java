package com.xule.feign.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Package com.xule.feign.consumer
 * @author: xule
 * @date: 2020/7/24 17:41
 */
@FeignClient(name = "nacos-provider",fallback = RemoteFallbackClient.class)
public interface RemoteClient {


    @GetMapping("hello")
    String hello();

}
