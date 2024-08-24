package org.ywk.person.api.facade;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.ywk.person.api.facade.entity.PersonSaveDTO;
import org.ywk.person.api.facade.entity.PersonVO;

import java.util.List;

@FeignClient(name = "hr-person-server",contextId = "hr-person-server")
public interface PersonFacade {


    @RequestMapping(value = "/listByCompanyId",method = RequestMethod.GET)
    List<PersonVO> listByCompanyId(@RequestParam("companyId")Long companyId);

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    Boolean save(@RequestBody List<PersonSaveDTO> list);



}
