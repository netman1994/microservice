package org.ywk.person.api.facade;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "hr-person-server",contextId = "hr-person-test-server"/*,configuration = MyfeignConfig.class*/)
public interface PersonTestFacade {



    @RequestMapping(value = "/test/open/api/getProperty",method = RequestMethod.GET)
    String getProperty(@RequestParam("key")String key);

}
