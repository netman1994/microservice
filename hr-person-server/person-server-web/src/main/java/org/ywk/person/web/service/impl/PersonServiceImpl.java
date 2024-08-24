package org.ywk.person.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.ywk.person.web.entity.Person;
import org.ywk.person.web.mapper.PersonMapper;
import org.ywk.person.web.service.PersonService;

@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements PersonService {
}
