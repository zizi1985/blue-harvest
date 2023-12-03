package com.blueharvest.assignment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author Z.Eskandari
 * created on 11/25/23
 */
@Profile("prod")
@Configuration
public class SwaggerConfiguration {
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.revised.controller"))
                .build()
                .apiInfo(metaData());
    }

    private ApiInfo metaData() {
        return new ApiInfo(
                "Spring Boot Account REST API",
                "Spring Boot REST API for Banking Application",
                "1.0",
                "Terms of service",
                new Contact("Zahra Eskandari", "http:/local", "test"),
                "Apache License Version 1.0",
                "https://www.apache.org/licenses/LICENSE-2.0");
    }
}
