package org.ywk.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtReactiveAuthenticationManager;
import org.springframework.security.oauth2.server.resource.web.server.ServerBearerTokenAuthenticationConverter;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    ReactiveJwtDecoder reactiveJwtDecoder;


    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

        http
                .authorizeExchange()

                .pathMatchers("/login","/oauth2/**").permitAll()
                .pathMatchers("/actuator/**",
                        "/instance",
                        "/",
                        "/favicon.ico",
                        "/error").permitAll()

                .anyExchange().authenticated()

                .and()
                .oauth2ResourceServer()
                .bearerTokenConverter(new ServerBearerTokenAuthenticationConverter())
                .jwt()
                // .jwtDecoder(reactiveJwtDecoder)
                .authenticationManager(new JwtReactiveAuthenticationManager(reactiveJwtDecoder))



        ;


        return http.build();

    }

}
