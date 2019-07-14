package com.reser.padel;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableJpaRepositories
@Slf4j
public class ReserPadelApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ReserPadelApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		log.info("Reserva de Pistas de Padel en marcha...");

	}

}
