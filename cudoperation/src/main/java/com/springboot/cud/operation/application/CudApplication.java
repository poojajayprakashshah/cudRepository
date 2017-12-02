package com.springboot.cud.operation.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.springboot.cud.operation")
public class CudApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(CudApplication.class, args);
	}

}

