package org.ywk.monitor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/monitor")
public class TestController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @GetMapping("/test")
    public String test() {
        return "test";
    }



    @GetMapping("/getFromCache/{key}")
    public String getFromCache(@PathVariable String key) {


        if (Boolean.TRUE.equals(stringRedisTemplate.hasKey(key))) {
            return stringRedisTemplate.opsForValue().get(key);
        }
        return "not exists";



    }





}
