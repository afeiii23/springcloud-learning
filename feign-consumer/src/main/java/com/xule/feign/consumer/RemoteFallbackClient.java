package com.xule.feign.consumer;

import org.springframework.stereotype.Component;

/**
 * @Package com.xule.feign.consumer
 * @author: xule
 * @date: 2020/7/27 14:31
 */
@Component
public class RemoteFallbackClient implements RemoteClient{


    /**
     * 降级服务
     * @return
     */
    @Override
    public String hello() {
        return "hello fallback";
    }
}
