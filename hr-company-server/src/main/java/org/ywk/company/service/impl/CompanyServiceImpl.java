package org.ywk.company.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.ywk.company.entity.Company;
import org.ywk.company.mapper.CompanyMapper;
import org.ywk.company.service.CompanyService;

@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {
}
