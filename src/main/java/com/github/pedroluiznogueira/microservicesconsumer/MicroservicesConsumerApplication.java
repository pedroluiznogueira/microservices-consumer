package com.github.pedroluiznogueira.microservicesconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MicroservicesConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesConsumerApplication.class, args);
		System.out.println("Running...");
	}

}
