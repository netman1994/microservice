package org.ywk.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.ywk.auth.entity.Role;
import org.ywk.auth.mapper.RoleMapper;
import org.ywk.auth.service.RoleService;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
}
