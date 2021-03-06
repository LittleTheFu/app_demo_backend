package com.fu.demo.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger2API文档的配置
 */
@Configuration
public class Swagger3Config {
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.OAS_30) // v2 不同
				.select().apis(RequestHandlerSelectors.basePackage("com.fu.demo")) // 设置扫描路径
				.build()
				.securitySchemes(securitySchemes())
				.securityContexts(securityContexts());
	}
//	@Bean
//	public Docket createRestApi() {
//		return new Docket(DocumentationType.OAS_30).apiInfo(apiInfo()).select()
//				// 为当前包下controller生成API文档
//				.apis(RequestHandlerSelectors.basePackage("com.fu.demo.controller"))
//				// 为有@Api注解的Controller生成API文档
////                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
//				// 为有@ApiOperation注解的方法生成API文档
////                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
////				.paths(PathSelectors.any()).build().securitySchemes(securitySchemes())
////				.securityContexts(securityContexts());
//				.build();
//	}
//
	private List<SecurityScheme> securitySchemes() {
		// 设置请求头信息
//		List<SecurityScheme> result = new ArrayList<SecurityScheme>();
		ApiKey apiKey = new ApiKey("Authorization", "Authorization", "header");
//		result.add(apiKey);
//		return result;
		return java.util.Collections.singletonList(apiKey);
	}
//
	private List<SecurityContext> securityContexts() {
		// 设置需要登录认证的路径
		List<SecurityContext> result = new ArrayList<>();
		result.add(getContextByPath("/greeting/.*"));
		return result;
	}
//	
	 private SecurityContext getContextByPath(String pathRegex){
	        return SecurityContext.builder()
	                .securityReferences(defaultAuth())
//	                .forPaths(PathSelectors.regex(pathRegex))
	                .build();
	    }
//
	    private List<SecurityReference> defaultAuth() {
	        List<SecurityReference> result = new ArrayList<>();
	        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
	        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
	        authorizationScopes[0] = authorizationScope;
	        result.add(new SecurityReference("Authorization", authorizationScopes));
	        return result;
	    }
//
////	private ApiInfo apiInfo() {
////		return new ApiInfoBuilder().title("SwaggerUI演示").description("demo").contact("macro").version("1.0").build();
////	}
}
