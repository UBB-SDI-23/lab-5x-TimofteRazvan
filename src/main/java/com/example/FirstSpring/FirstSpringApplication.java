package com.example.FirstSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FirstSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstSpringApplication.class, args);
	}

	/*
	@Bean
	public CommandLineRunner initialCreate(EmployeeService employeeService) {
		return (args) -> {};
	}
	 */
}
