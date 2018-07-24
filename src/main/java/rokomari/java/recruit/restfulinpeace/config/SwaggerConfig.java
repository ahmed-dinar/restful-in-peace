package rokomari.java.recruit.restfulinpeace.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import springfox.documentation.service.Contact;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket dataApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("api")
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.ant("/api/**"))
				.build()
				.apiInfo(metaInfo());
	}
	
	
	@Bean
	public Docket loginApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("login")
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.ant("/login/**"))
				.build()
				.apiInfo(metaInfo());
	}
	
	@Bean
	public Docket registerApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("register")
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.ant("/register/**"))
				.build()
				.apiInfo(metaInfo());
	}


	private ApiInfo metaInfo() {
		return new ApiInfoBuilder().title("Rokomari RESTful-in-peace Project API")
				.description("\"Spring Boot API for recruitment\"").version("0.0.1")
				.license("Apache License Version 2.0").licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
				.contact(new Contact("Ahmed Dinar", "https://ahmeddinar.me/", "madinar.cse@gmail.com")).build();
	}
}
