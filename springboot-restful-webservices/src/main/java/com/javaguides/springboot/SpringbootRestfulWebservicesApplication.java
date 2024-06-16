package com.javaguides.springboot;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
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
