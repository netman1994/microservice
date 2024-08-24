package org.ywk.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.ywk.auth.service.UserService;

import javax.sql.DataSource;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

@EnableAuthorizationServer
@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {


    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    DataSource dataSource;

    @Autowired
    UserService userService;

    @Value("${security.oauth2.resource.jwt.key-value}")
    private String keyValue;

    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @Bean
    public JwtAccessTokenConverter accessTokenConverter(KeyPair keyPair) {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(keyPair);
        return converter;
    }

    @Bean
    public TokenStore tokenStore(JwtAccessTokenConverter accessTokenConverter) {
        //
         RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
         return tokenStore;

        // return new JwtTokenStore(accessTokenConverter);
    }

    @Bean
    public KeyPair keyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            return keyPairGenerator.generateKeyPair();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Bean
    public ClientDetailsService clientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                ;
    }





    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                /*.inMemory()
                .withClient("hr-client")
                .secret("{noop}hr-secret")
                .authorizedGrantTypes("password","authorization_code", "refresh_token","implicit")
                .scopes("all")
                .accessTokenValiditySeconds(120)
                .refreshTokenValiditySeconds(2592000)*/
                .withClientDetails(clientDetailsService())
                ;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager)
                .accessTokenConverter(accessTokenConverter(keyPair()))
                .tokenStore(tokenStore(accessTokenConverter(keyPair())))
                .userDetailsService(userService) // fix: refresh_token 报错 （https://blog.csdn.net/zhoudingding/article/details/107971094）
        ;
        ;
    }
}
