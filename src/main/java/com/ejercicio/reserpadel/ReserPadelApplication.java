package com.ejercicio.reserpadel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class ReserPadelApplication{// implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(ReserPadelApplication.class, args);
	}

	public void run(String... args) throws Exception {
		// 1st version. Not working yet...
	}

}
