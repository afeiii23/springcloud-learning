package com.xule.controller;

import com.xule.LoginUserHolder;
import com.xule.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package com.mhc.controller
 * @author: xule
 * @date: 2020/7/23 17:09
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    LoginUserHolder loginUserHolder;

    @GetMapping("/currentUser")
    public UserDTO currentUser() {
        return loginUserHolder.getCurrentUser();
    }

}
