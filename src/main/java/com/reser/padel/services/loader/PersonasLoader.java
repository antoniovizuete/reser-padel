package com.reser.padel.services.loader;

import java.util.Iterator;
import java.util.Random;
import java.util.stream.IntStream;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.reser.padel.domain.Persona;
import com.reser.padel.repositories.PersonaRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Profile("!test")
public class PersonasLoader {

	private PersonaRepository personaRepository;

	public PersonasLoader(PersonaRepository personaRepository) {
		super();
		this.personaRepository = personaRepository;
	}
	
	public void cargaInicial() {

		if (personaRepository.count() > 0) {
			log.info("Ya existen personas en la tabla.");
			return;
		}
		
		JsonObject jsonObject = null;
		ApiSW apiSW = new ApiSW();
		
		try {
			jsonObject = apiSW.getBuilder("people");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        JsonArray peopleResults = jsonObject.getAsJsonArray("results");
        
        if (peopleResults.size() != 0) {

        	Random random = new Random();
        	IntStream intStream = random.ints(peopleResults.size(), 0, 11);
        	Iterator<Integer> iterator = intStream.iterator();

        	for (JsonElement personResult : peopleResults) {
            	
            	JsonObject people = personResult.getAsJsonObject();
            	
            	Persona persona = new Persona();
            	persona.setEmail(people.get("name").getAsString().replace(" ", ".").concat("@email.com"));
            	persona.setNombre(people.get("name").getAsString());
            	persona.setNivelPadel(iterator.next());
            	
            	personaRepository.save(persona);
            	log.info("Tabla 'PERSONAS' cargada correctamente!");
            }
            
        } else {
        	log.info("No se ha recuperado ningún registro externo.");
        }
            
	}
	
}
