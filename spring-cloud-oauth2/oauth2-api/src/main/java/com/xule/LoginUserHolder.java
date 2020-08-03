package com.xule;

import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONObject;
import com.xule.dto.UserDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Package com.mhc.controller
 * @author: xule
 * @date: 2020/7/23 16:54
 */
@Component
public class LoginUserHolder {

    public UserDTO getCurrentUser() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        // 从request header中获取用户信息
        String userStr = request.getHeader("user");
        JSONObject userJson = new JSONObject(userStr);
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(Convert.toLong(userJson.get("id")));
        userDTO.setUserName(userJson.getStr("user_name"));
        userDTO.setRoles(Convert.toList(String.class,userJson.get("authorities")));
        return userDTO;
    }
}
