package com.qa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.qa.persistence.dtos.TaskDTO;

@SpringBootApplication
public class TdlProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(TdlProjectApplication.class, args);
		System.out.println("Finished loading...");
		
	}

}
