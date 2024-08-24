package org.ywk.auth.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@TableName(value = "tab_user")
@Data
public class User implements UserDetails {

    @TableId(type = IdType.AUTO)
    public Long id;

    public String username;

    public String password;

    public String name;

    private Boolean enable;

    private Boolean locked;


    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    @TableField(exist = false)
    private List<Role> roles;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return roles.stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r.getName()))
                .collect(Collectors.toList());

        // return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return ! locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }
}
