package com.reser.padel;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.reser.padel.services.loaders.LoaderService;

@SpringBootApplication
@EnableJpaRepositories
public class PadelApplication implements CommandLineRunner {
	
	private LoaderService loaderService;
	
	public PadelApplication(LoaderService loaderService) {
		super();
		this.loaderService = loaderService;
	}

	public static void main(String[] args) {
		SpringApplication.run(PadelApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		loaderService.load();
	}

}