package org.ywk.company;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@MapperScan(basePackages = {"org.ywk.company.mapper"})
@EnableFeignClients(basePackages = "org.ywk.*")
@ComponentScan(basePackages = {"org.ywk.*"})
@SpringBootApplication
public class CompanyApplication {

    public static void main(String[] args) {

        SpringApplication.run(CompanyApplication.class,args);

    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
