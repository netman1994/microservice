package org.ywk.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.ywk.auth.entity.User;

public interface UserService extends IService<User>, UserDetailsService {
}
