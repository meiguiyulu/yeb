package com.lyj.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2配置类
 */
@Configuration
@EnableSwagger2 // 开启Swagger配置扫描
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                // 扫描哪个接口的包
                .apis(RequestHandlerSelectors.basePackage("com.lyj.server.controller"))
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfoBuilder()
                        .title("云E办")
                        .description("云E办接口文档")
                        // 版本信息
                        .version("1.0")
                        // 开发文档的联系人
                        .contact(new Contact("撷思", "http:localhost:8081/doc.html", "1609226090@qq.com"))
                        .license("This Baidu License")
                        .licenseUrl("http://www.baidu.com")
                        .build());
    }
}