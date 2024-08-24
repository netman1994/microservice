package org.ywk.gateway;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import java.util.Arrays;

@SpringBootTest
public class GatewayApplicationTest {

    @Autowired
    public Environment environment;

    @Test
    public void contextLoads() {
        Arrays.stream(environment.getDefaultProfiles()).forEach(System.out::println);
    }
}
