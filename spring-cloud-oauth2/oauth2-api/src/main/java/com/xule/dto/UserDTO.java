package com.xule.dto;

import lombok.Data;

import java.util.List;

/**
 * @Package com.xule.dto
 * @author: xule
 * @date: 2020/7/23 11:53
 */
@Data
public class UserDTO {

    private Long userId;

    private String userName;

    private String password;

    private Integer status;

    private List<String> roles;
}
