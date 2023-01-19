package com.inseefr.acdc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableLoadTimeWeaving;

@SpringBootApplication
public class AcdcApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcdcApplication.class, args);
	}

}
