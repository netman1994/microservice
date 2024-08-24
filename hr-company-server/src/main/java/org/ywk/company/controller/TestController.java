package org.ywk.company.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.ywk.common.result.ResultResponse;
import org.ywk.company.entity.Company;
import org.ywk.company.entity.dtos.CompanySaveDTO;
import org.ywk.company.entity.vos.CompanyVO;
import org.ywk.company.service.CompanyService;
import org.ywk.person.api.facade.PersonFacade;
import org.ywk.person.api.facade.PersonTestFacade;
import org.ywk.person.api.facade.entity.PersonVO;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


@RestController
public class TestController {

    @Autowired
    PersonTestFacade personTestFacade;

    @Autowired
    Environment environment;

    @Autowired
    PersonFacade personFacade;

    @Resource
    CompanyService companyService;

    @GetMapping("/getProperty/{key}")
    public ResultResponse<String> getProperty(@PathVariable("key") String key) {
        String property = personTestFacade.getProperty(key);
        if (StringUtils.isBlank(property)) {
            property = environment.getProperty(key,"not found");
        }
        return ResultResponse.success(property);
    }

    @GetMapping("/getCompanyEmployeeById/{id}")
    public ResultResponse<List<PersonVO>> getCompanyEmployeeById(@PathVariable("id") Long id) {


        List<PersonVO> vos = personFacade.listByCompanyId(id);

        return ResultResponse.success(vos);
    }

    @PostMapping("/saveCompanyEmployee")
    public ResultResponse<Boolean> saveCompanyEmployee(@RequestBody CompanySaveDTO saveDTO) {


        Company company = new Company();

        company.setCompanyName(saveDTO.getCompanyName());
        company.setCreditCode(saveDTO.getCreditCode());
        company.setAddress(saveDTO.getAddress());
        company.setLegalName(saveDTO.getLegalPerson());
        company.setCreateTime(LocalDateTime.now());
        company.setUpdateTime(LocalDateTime.now());

        boolean save = companyService.save(company);

        if (save) {
            saveDTO.getPersons().forEach(p -> p.setCompanyId(company.getId()));
            return ResultResponse.success(personFacade.save(saveDTO.getPersons()));
        }

        return ResultResponse.fail();


    }


    @GetMapping("/getCompanyDetail/{id}")
    public ResultResponse<CompanyVO> getCompanyDetail(@PathVariable("id") Long id) {

        CompanyVO vo = new CompanyVO();

        Company company = companyService.getById(id);
        if (Objects.isNull(company)) {
            return ResultResponse.success();
        }

        BeanUtils.copyProperties(company,vo);

        List<PersonVO> persons = personFacade.listByCompanyId(company.getId());
        vo.setPersons(persons);


        return ResultResponse.success(vo);


    }
}
