package org.ywk.person.web.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.spring.web.plugins.WebFluxRequestHandlerProvider;
import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

//@EnableSwagger2
@Configuration
public class Swagger2Config {


    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.ywk.person.web.controller")) // 包路径不能写错
                .paths(PathSelectors.any())
                .build()
                ;
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("hr-person-server")
                .description("用户服务")
                .contact(new Contact("hr","http://tanximei.com","netman1994@163.com"))
                .version("1.0")
                .build();
    }



//    @Bean
    /*public BeanPostProcessor beanPostProcessor() {
        return new BeanPostProcessor() {
            @Override
            public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

                if (bean instanceof WebMvcRequestHandlerProvider || bean instanceof WebFluxRequestHandlerProvider) {
                    customizeSpringfoxHandlerMappings(getHandlerMappings(bean));
                }

                return bean;
            }

            private <T extends RequestMappingInfoHandlerMapping> void customizeSpringfoxHandlerMappings(List<T> mappings) {
                List<T> collect = mappings.stream()
                        .filter(m -> m.getPatternParser() == null)
                        .toList();

                mappings.clear();
                mappings.addAll(collect);
            }

            private List<RequestMappingHandlerMapping> getHandlerMappings(Object bean) {
                List<RequestMappingHandlerMapping> handlerMappings = new ArrayList<>();
                try {
                    Field field = bean.getClass().getDeclaredField("handlerMappings");
                    field.setAccessible(true);
                    return ((List<RequestMappingHandlerMapping>) field.get(bean));
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }


        };
    }*/

}
