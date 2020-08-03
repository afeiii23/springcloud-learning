package com.xule.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.xule.dto.SecurityUser;
import com.xule.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Package com.xule.service.impl
 * @author: xule
 * @date: 2020/7/23 11:48
 */
@Service
public class UserServiceImpl implements UserDetailsService {

    private List<UserDTO> userDTOList;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 初始化 mock数据
     */
    @PostConstruct
    public void initData(){
        String password = passwordEncoder.encode("123");
        userDTOList = new ArrayList<>();
        userDTOList.add(new UserDTO(1L,"xule",password,1, CollUtil.toList("admin")));
        userDTOList.add(new UserDTO(2L,"xule1",password,1, CollUtil.toList("test")));
    }


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        List<UserDTO> userDTOS = userDTOList.stream().filter(e -> e.getUserName().equals(userName)).collect(Collectors.toList());
        if (CollectionUtil.isEmpty(userDTOS)) {
            throw new UsernameNotFoundException("账号密码错误");
        }
        SecurityUser securityUser = new SecurityUser(userDTOS.get(0));
        if (!securityUser.isEnabled()) {
            throw new DisabledException("账号无效");
        }
        if (!securityUser.isAccountNonLocked()) {
            throw new LockedException("账号已被锁定");
        }
        if (!securityUser.isAccountNonExpired()) {
            throw new AccountExpiredException("账号过期");
        }
        if (!securityUser.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException("登录凭证已过期");
        }
        return securityUser;
    }
}
