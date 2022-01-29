package com.initsrc.core.module.swagger;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * swagger 配置
 * 作者：INITSRC (启源)
 */
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
@Profile({"dev", "test", "prod"})
public class SwaggerConfig {

    @Value("${initsrc.swagger.show}")
    private boolean show;

    @Value("${initsrc.swagger.name}")
    private String name;

    @Value("${initsrc.swagger.url}")
    private String url;

    @Value("${initsrc.swagger.email}")
    private String email;

    @Value("${initsrc.swagger.title}")
    private String title;

    @Value("${initsrc.swagger.description}")
    private String description;

    @Value("${initsrc.swagger.version}")
    private String version;

    @Value("${initsrc.swagger.termsOfServiceUrl}")
    private String termsOfServiceUrl;

    @Value("${initsrc.swagger.contact}")
    private String contact;

    @Value("${initsrc.swagger.license}")
    private String license;

    @Value("${initsrc.swagger.licenseUrl}")
    private String licenseUrl;

    @Bean
    public Docket createDevRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("INITSRC 开发者接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.initsrc.dev"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket createCommonRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("INITSRC 公共接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.initsrc.common"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket createAdminRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("INITSRC 后台管理接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.initsrc.admin"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(title).description(description)  //自定义标题和说明
                .termsOfServiceUrl(termsOfServiceUrl)   //指定访问的URL
                .contact(new Contact(name, url, email)).build();
    }
}
