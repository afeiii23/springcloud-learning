package com.xule.dto;

import cn.hutool.core.collection.CollectionUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @Package com.xule.dto
 * @author: xule
 * @date: 2020/7/23 14:18
 */
@Data
@NoArgsConstructor
public class SecurityUser implements UserDetails {

    private Long userId;

    private String userName;

    private String password;

    /**
     * 是否启用
     */
    private Boolean enabled;

    /**
     * 权限数据
     */
    private Collection<SimpleGrantedAuthority> authorities;


    public SecurityUser(UserDTO userDTO) {
        SecurityUser securityUser = new SecurityUser();
        securityUser.setUserId(userDTO.getUserId());
        securityUser.setUserName(userDTO.getUserName());
        securityUser.setPassword(userDTO.getPassword());
        securityUser.setEnabled(Integer.valueOf(1).equals(userDTO.getStatus()));
        if (CollectionUtil.isNotEmpty(userDTO.getRoles())) {
            securityUser.setAuthorities(userDTO.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
