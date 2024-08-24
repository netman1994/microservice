package org.ywk.company.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ywk.common.result.ResultResponse;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public ResultResponse<String> hello() {

        return ResultResponse.success("hello");

    }
}
