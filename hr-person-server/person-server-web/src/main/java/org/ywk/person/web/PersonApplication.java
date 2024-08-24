package org.ywk.person.web;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "org.ywk.person.web.mapper")
@SpringBootApplication
public class PersonApplication {
    public static void main(String[] args) {
        SpringApplication.run(PersonApplication.class,args);
    }
}
