package com.javaguides.springboot;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
	info = @Info(
		title = "Spring Boot REST API Documentation",
		description = "Spring Boot REST API Documentation",
		version = "v1.0",
		contact = @Contact(
			name = "Rahul Gupta",
			email = "rkgdec2000@gmail.com"
		)
	),
	externalDocs = @ExternalDocumentation(
		description = "Spring Boot User Management Documentation",
		url=""
		
	)

)
public class SpringbootRestfulWebservicesApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootRestfulWebservicesApplication.class, args);
		System.out.println("Welcome to SpringBoot Restful's webservices");
	}


	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}
