package com.reser.padel.services.loader;

import java.time.LocalTime;
import java.util.Iterator;
import java.util.Random;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.reser.padel.domain.Pista;
import com.reser.padel.repositories.PistaRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Profile("!test")
public class PistasLoader {

	private PistaRepository pistaRepository;
		
	public PistasLoader(PistaRepository pistaRepository) {
		super();
		this.pistaRepository = pistaRepository;
	}

	public void cargaInicial() {

		if (pistaRepository.count() > 0) {
			log.info("Ya existen pistas en la tabla.");
			return;
		}
		
		JsonObject jsonObject = null;
		ApiSW apiSW = new ApiSW();
		
		try {
			jsonObject = apiSW.getBuilder("planets");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        JsonArray planetResults = jsonObject.getAsJsonArray("results");
        
        if (planetResults.size() != 0) {

        	Random random = new Random(System.currentTimeMillis());
        	IntStream intStreamApertura = random.ints(planetResults.size(), 8, 15);
        	IntStream intStreamCierre   = random.ints(planetResults.size(), 15, 23);
        	DoubleStream doubleStreamPrecio = random.doubles(planetResults.size(), 0, 1000/50);

        	Iterator<Integer> iteratorApertura = intStreamApertura.iterator();
        	Iterator<Integer> iteratorCierre   = intStreamCierre.iterator();
        	Iterator<Double> iteratorPrecio    = doubleStreamPrecio.iterator();

        	for (JsonElement planetResult : planetResults) {
            	
            	JsonObject planet = planetResult.getAsJsonObject();
            	LocalTime apertura = LocalTime.of(iteratorApertura.next(), 0, 0);
            	LocalTime cierre   = LocalTime.of(iteratorCierre.next(), 0, 0);
            	
            	Pista pista = new Pista();
            	pista.setNombre(planet.get("name").getAsString());
            	pista.setApertura(apertura);
            	pista.setCierre(cierre);
            	pista.setPrecio((Math.floor(iteratorPrecio.next())*50)/100);
            	
            	pistaRepository.save(pista);
            	log.info("Tabla 'PISTAS' cargada correctamente!");
            }
            
        } else {
        	log.info("No se ha recuperado ningún registro externo.");
        }

	}
	
}
