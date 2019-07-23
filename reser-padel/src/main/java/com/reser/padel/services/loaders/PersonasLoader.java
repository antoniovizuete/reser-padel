package com.reser.padel.services.loaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reser.padel.domain.Persona;
import com.reser.padel.repositories.PersonaRepository;

@Service
public class PersonasLoader {

	private static Logger logger = LoggerFactory.getLogger(PersonasLoader.class);
	
	private PersonaRepository repository;

	@Autowired
	public PersonasLoader(PersonaRepository repository) {
		this.repository = repository;
	}
	
	public void load() {
		if(repository.count() > 0) {
			logger.info("Ya existen personas.");
			return;
		}
		
		List<Persona> personas = new ArrayList<>();
		
		Persona persona = new Persona();
		persona.setEmail("ruben.correo@everis.com");
		persona.setNombre("Ruben Navarro");
		persona.setNivelPadel(5);		
		personas.add(persona);
		
		persona = new Persona();
		persona.setEmail("pedro.correo@everis.com");
		persona.setNombre("Pedro Gimenez");
		persona.setNivelPadel(5);		
		personas.add(persona);
		
		persona = new Persona();
		persona.setEmail("marta.correo@everis.com");
		persona.setNombre("Marta Rodrguez");
		persona.setNivelPadel(8);		
		personas.add(persona);
		
		persona = new Persona();
		persona.setEmail("jose.correo@everis.com");
		persona.setNombre("Jose Perez");
		persona.setNivelPadel(2);		
		personas.add(persona);
		
		persona = new Persona();
		persona.setEmail("marta2.correo@everis.com");
		persona.setNombre("Marta Rodrguez");
		persona.setNivelPadel(3);		
		personas.add(persona);
		
		persona = new Persona();
		persona.setEmail("mateo.correo@everis.com");
		persona.setNombre("Mateo Gonzalez");
		persona.setNivelPadel(8);		
		personas.add(persona);
		
		persona = new Persona();
		persona.setEmail("soyte.correo@everis.com");
		persona.setNombre("Soyte Nista");
		persona.setNivelPadel(99);		
		personas.add(persona);
		
		persona = new Persona();
		persona.setEmail("rafa.correo@everis.com");
		persona.setNombre("Rafa Buceal");
		persona.setNivelPadel(999);		
		personas.add(persona);
		
		
		
		logger.info("Cargando personas: {}", personas);
		repository.saveAll(personas);
	}
}
