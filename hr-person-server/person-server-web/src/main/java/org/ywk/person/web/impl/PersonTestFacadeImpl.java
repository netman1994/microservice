package org.ywk.person.web.impl;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RestController;
import org.ywk.person.api.facade.PersonTestFacade;

import javax.annotation.Resource;


@RestController
public class PersonTestFacadeImpl implements PersonTestFacade {


    @Resource
    private Environment environment;

    @Override
    public String getProperty(String key) {
        return environment.getProperty(key,"not found");
    }
}
