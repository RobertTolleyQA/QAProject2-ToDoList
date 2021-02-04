package com.qa.config;

import java.time.LocalTime;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

public class AppConfig {
	
	@Bean
	public String getTime() {
		return LocalTime.now().toString();
		
}
	
	@Bean
	public ModelMapper getMapper() {
		return new ModelMapper();
	}

}
