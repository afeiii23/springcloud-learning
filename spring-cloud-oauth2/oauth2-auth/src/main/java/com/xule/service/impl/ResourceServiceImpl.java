package com.xule.service.impl;

import cn.hutool.core.collection.CollUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Package com.xule.service.impl
 * @author: xule
 * @date: 2020/7/23 15:25
 */
@Service
public class ResourceServiceImpl {

    private Map<String, List<String>> resourceRoleMap;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 初始化角色与资源的匹配关系
     */
    @PostConstruct
    public void initData() {
        resourceRoleMap = new TreeMap<>();
        resourceRoleMap.put("/api/hello", CollUtil.toList("admin"));
        resourceRoleMap.put("/api/user/currentUser", CollUtil.toList("admin","test"));
        redisTemplate.opsForHash().putAll("role_source_key",resourceRoleMap);
    }
}
