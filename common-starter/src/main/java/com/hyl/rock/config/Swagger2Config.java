package com.hyl.rock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalTime;

@EnableSwagger2
@Profile({"local","test"})
@Configuration
public class Swagger2Config {

    @Bean
    public Docket createDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo("应用接口"))
                .directModelSubstitute(LocalTime.class, String.class)
                .groupName("应用接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hyl.test.controller"))
                .paths(PathSelectors.any())
                .build();
    }


    private ApiInfo apiInfo(String title) {
        return new ApiInfoBuilder()
                .title(title)
                .version("1.0")
                .build();
    }
}
