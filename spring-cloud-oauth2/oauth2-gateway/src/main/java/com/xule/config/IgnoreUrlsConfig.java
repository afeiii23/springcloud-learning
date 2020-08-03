package com.xule.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Package com.xule.config
 * @author: xule
 * @date: 2020/7/23 16:01
 */
@Data
@ConfigurationProperties(prefix = "secure.ignore")
@Component
public class IgnoreUrlsConfig {
    private List<String> urls;
}
