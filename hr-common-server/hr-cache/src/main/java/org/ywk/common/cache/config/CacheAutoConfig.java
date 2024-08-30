package org.ywk.common.cache.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class CacheAutoConfig {



    @PostConstruct
    public void init() {

        System.out.println("----redis init----");

    }


}
