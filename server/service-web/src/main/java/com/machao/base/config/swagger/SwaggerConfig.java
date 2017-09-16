package com.machao.base.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.machao.sp"))
                .paths(PathSelectors.any())
                .build();
	}

	private ApiInfo apiInfo() {
		 return new ApiInfoBuilder()
	                .title("Steam Pair APIs")
	                .description("provide restful api")
//	                .termsOfServiceUrl("http://blog.didispace.com/")
//	                .contact("程序猿DD")
	                .version("0.0.1-SNAPSHOT")
	                .build();
	}
}
