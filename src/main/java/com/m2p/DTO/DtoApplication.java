package com.m2p.DTO;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DtoApplication {

	/* There are 3 steps of using Model Mapper
	*  1. Add the Model Mapper Dependency
	*  2. Configure a Bean of Model Mapper
	*  3. Inject it and use it in service class */


	/* There are 3 steps of using MapStruct Library
	*  1. Add the MapStruct Maven Dependency
	*  2. Create UserMapper using MapStruct
	*  3. Use UserMapper in Service Implementation class
	* */

	// Configuring a Bean for Model Mapper so that it can be injected and used
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(DtoApplication.class, args);
	}

}
