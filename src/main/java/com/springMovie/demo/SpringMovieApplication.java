package com.springMovie.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringMovieApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMovieApplication.class, args);
		
		System.out.println("+++APPLICATION STARTED+++");
	}

}
