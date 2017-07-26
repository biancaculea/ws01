package com.example;

import com.google.common.base.Predicates;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.*;

@SpringBootApplication
@EnableSwagger2
public class WsssApplication {

	public static void main(String[] args) {
		SpringApplication.run(WsssApplication.class, args);
	}

	@Bean
	public Docket swaggerSettings() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				//.paths(PathSelectors.any())
				.paths(Predicates.not(PathSelectors.regex("/error"))) // elimina basic error controller
				.build()
				.pathMapping("/");
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				//registry.addMapping("/v2/api-docs/").allowedOrigins("http://localhost:8080");
				//registry.addMapping("/v2/api-docs/");
				registry.addMapping("/greeting").allowedOrigins("http://localhost:9000");
			}
		};
	}
	//  obs we only allow http://localhost:9000 to send cross-origin requests..

}
