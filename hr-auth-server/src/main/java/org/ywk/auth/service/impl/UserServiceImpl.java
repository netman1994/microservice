package org.ywk.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.ywk.auth.entity.Role;
import org.ywk.auth.entity.User;
import org.ywk.auth.mapper.UserMapper;
import org.ywk.auth.service.UserService;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = getOne(new QueryWrapper<User>().lambda()
                .eq(User::getUsername, username));

        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException(username);
        }

        // todo
        user.setRoles(List.of(new Role()));


        return user;
    }
}
