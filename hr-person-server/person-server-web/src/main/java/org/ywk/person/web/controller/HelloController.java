package org.ywk.person.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.ywk.common.result.ResultResponse;
import org.ywk.person.web.config.RedisConfig;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Slf4j(topic = "helloController")
@Api(tags = "helloController")
//@Tag(name = "test",description = "测试")
@RestController
public class HelloController {

    @Resource
    Environment environment;

    @ApiOperation(value = "hello1接口")
    @GetMapping("/hello")
    public ResultResponse<String> hello(String name) {
        return ResultResponse.success(String.format("hello %s !", name));
    }

    @ApiOperation("获取 environment 中的 property")
    @GetMapping("/getProperty/{key}")
    public ResultResponse<String> getProperty(@PathVariable("key") String key) {
        return ResultResponse.success(environment.getProperty(key,"not found"));
    }


    @ApiOperation("测试 '@Cacheable' 注解")
    @Cacheable(value = RedisConfig.REDIS_KEY_PREFIX,key = "'per:cacheable:#id'",unless = "#result != null")
    @GetMapping("redis/ann/cacheable")
    public ResultResponse<String> cacheable(Long id) throws JsonProcessingException {
        Map<String,Object> result = new HashMap<>();

        result.put("id",id);
        result.put("timestamp",System.currentTimeMillis());

        String jsonStr = new ObjectMapper().writeValueAsString(result);

        log.info("cacheable:{}",jsonStr);
        return ResultResponse.success(jsonStr);

    }

}
