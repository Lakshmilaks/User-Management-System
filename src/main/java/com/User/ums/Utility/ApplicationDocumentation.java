package com.User.ums.Utility;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
//@EnableSwagger2
@Configuration
@OpenAPIDefinition
public class ApplicationDocumentation {
	
	Contact contact() {
		return new Contact().email("ram@gmail.com")
				.url("xyz.ram.in")
				.email("ram");
	}

	Info info() {
		return new Info()
				.title("User Management System API")
				.version("1.0v")
				.description("User Management System is a RESTful API built using"
				              +" Sping Boot and MySQL Database")
		.contact(contact());
	}
	@Bean
	OpenAPI openAPI() {
		return new OpenAPI().info(info());
	}
	
//	 @Bean
//	    public Docket api() {
//	        return new Docket(DocumentationType.SWAGGER_2)
//	                .select()
//	                .apis(RequestHandlerSelectors.basePackage("com.User.ums.Controller"))
//	                .paths(PathSelectors.any())
//	                .build()
//	                .useDefaultResponseMessages(false); // Disable default response messages
//	    }
}
