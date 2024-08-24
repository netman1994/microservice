package org.ywk.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ywk.common.result.ResultResponse;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public ResultResponse<String> hello() {

        return ResultResponse.success("hello");

    }



    @GetMapping("/admin/hello")
    public ResultResponse<String> hello1() {

        return ResultResponse.success("/admin/hello");

    }


    @GetMapping("/dba/hello")
    public ResultResponse<String> hello2() {

        return ResultResponse.success("/dba/hello");

    }


    @GetMapping("/user/hello")
    public ResultResponse<String> hello3() {

        return ResultResponse.success("/user/hello");

    }
}
