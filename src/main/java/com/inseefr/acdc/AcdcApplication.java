package com.inseefr.acdc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableLoadTimeWeaving;

@SpringBootApplication
@ComponentScan(basePackages = {"com.inseefr.acdc"})
public class AcdcApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcdcApplication.class, args);
	}

}
