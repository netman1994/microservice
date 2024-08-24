package org.ywk.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.ywk.auth.service.UserService;

@EnableWebSecurity
@Configurable
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    UserService userService;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        // return super.userDetailsService();
        return userService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                /*.inMemoryAuthentication()
                .withUser("user")
                .password("{noop}user")
                .roles("USER")*/
                .userDetailsService(userService)
                ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()

                .antMatchers("/oauth/**","/.well-known/jwks.json").permitAll()
                .antMatchers("/actuator/**",
                        "/instance",
                        "/",
                        "/favicon.ico",
                        "/error").permitAll() // 监控服务需要访问的端口放行

                .anyRequest().authenticated()

                .and()
                .csrf()
                .disable()
                ;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
