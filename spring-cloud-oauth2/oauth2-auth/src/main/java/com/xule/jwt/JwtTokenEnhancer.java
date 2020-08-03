package com.xule.jwt;

import com.xule.dto.SecurityUser;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 往jwt中添加自定义的信息
 * @Package com.xule
 * @author: xule
 * @date: 2020/7/23 14:38
 */
@Component
public class JwtTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication authentication) {
        SecurityUser securityUser = (SecurityUser)authentication.getPrincipal();
        // 将用户id设置进JWT
        Map<String, Object> map = new HashMap<>();
        map.put("id",securityUser.getUserId());
        ((DefaultOAuth2AccessToken)oAuth2AccessToken).setAdditionalInformation(map);
        return oAuth2AccessToken;
    }
}
