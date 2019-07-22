package com.everis.cestevez.padel;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class PadelApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PadelApplication.class, args);
	}

	public void run(String... args) throws Exception {
		
	}
}
