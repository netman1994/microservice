package org.ywk.person.web.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RestController;
import org.ywk.person.api.facade.PersonFacade;
import org.ywk.person.api.facade.entity.PersonSaveDTO;
import org.ywk.person.api.facade.entity.PersonVO;
import org.ywk.person.web.entity.Person;
import org.ywk.person.web.service.PersonService;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j(topic = "PersonFacadeImpl")
@RestController
public class PersonFacadeImpl implements PersonFacade {

    @Resource
    PersonService personService;

    @Override
    public List<PersonVO> listByCompanyId(Long companyId) {

        log.info("listByCompanyId execute......");

        return personService.list(new QueryWrapper<Person>()
                        .lambda()
                        .eq(Person::getSbCompanyId, companyId))
                .stream()
                .map(p -> {
                    PersonVO vo = new PersonVO();
                    BeanUtils.copyProperties(p, vo);
                    vo.setSex(p.getPersonSex() == null ? "未知" : (p.getPersonSex() == 1 ? "男" : "女"));
                    return vo;
                }).collect(Collectors.toList());

    }

    @Override
    public Boolean save(List<PersonSaveDTO> list) {

        log.info("save execute......");

        LocalDateTime now = LocalDateTime.now();
        List<Person> persons = list.stream().map(l -> {
            Person person = new Person();
            person.setSbCompanyId(l.getCompanyId());
            person.setFundCompanyId(l.getCompanyId());
            person.setSocialCompanyId(l.getCompanyId());
            person.setPersonName(l.getName());
            person.setPersonSex(l.getSex());
            person.setPersonalIdCard(l.getIdCard());
            person.setCreateTime(now);
            person.setCreateTime(now);
            return person;
        }).toList();


        return personService.saveBatch(persons);
    }
}
