package com.yyfly.library.config;

import com.yyfly.library.constant.CompanyProperties;
import com.yyfly.library.constant.ProjectProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;

import static com.google.common.collect.Lists.newArrayList;

/**
 * 接口文档
 *
 * @author : yyfly / developer@yyfly.com
 * @date   : 2018-08-08
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public ProjectProperties projectProperties() {
        return new ProjectProperties();
    }

    @Bean
    public CompanyProperties companyProperties() {
        return new CompanyProperties();
    }


    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(projectProperties().getName())
                .description(projectProperties().getDescription())
                .license(projectProperties().getLicense())
                .licenseUrl(projectProperties().getLicenseUrl())
                .termsOfServiceUrl("")
                .version(projectProperties().getVersion())
                .contact(new Contact(companyProperties().getName(), companyProperties().getWebsite(), companyProperties().getEmail()))
                .build();
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .build()
                .pathMapping("/")
                .directModelSubstitute(LocalDate.class, String.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET,newArrayList(new ResponseMessageBuilder().code(500).message("500 message").responseModel(new ModelRef("Error")).build()));
    }

}
