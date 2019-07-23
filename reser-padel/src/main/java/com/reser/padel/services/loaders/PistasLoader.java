package com.reser.padel.services.loaders;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reser.padel.domain.Pista;
import com.reser.padel.repositories.PistaRepository;

@Service
public class PistasLoader {

	private static Logger logger = LoggerFactory.getLogger(PistasLoader.class);
	
	private PistaRepository repository;

	@Autowired
	public PistasLoader(PistaRepository repository) {
		this.repository = repository;
	}
	
	public void load() {
		if(repository.count() > 0) {
			logger.info("Ya existen pistas.");
			return;
		}
		
		List<Pista> pistas = new ArrayList<>();
		
		Pista pista = new Pista();
		pista.setNombre("Raquetazo'nojo");	
		pista.setApertura(LocalTime.parse("10:00"));
		pista.setCierre(LocalTime.parse("18:00"));
		pista.setPrecio(5.0);
		pistas.add(pista);
		
		pista = new Pista();
		pista.setNombre("Anis Tenis");	
		pista.setApertura(LocalTime.parse("11:00"));
		pista.setCierre(LocalTime.parse("19:00"));
		pista.setPrecio(7.5);
		pistas.add(pista);
		
		pista = new Pista();
		pista.setNombre("Padel'antar tiempo");	
		pista.setApertura(LocalTime.parse("09:00"));
		pista.setCierre(LocalTime.parse("15:00"));
		pista.setPrecio(3.0);
		pistas.add(pista);
		
		pista = new Pista();
		pista.setNombre("Esraque Tanopala");	
		pista.setApertura(LocalTime.parse("12:00"));
		pista.setCierre(LocalTime.parse("21:00"));
		pista.setPrecio(2.5);
		pistas.add(pista);
		
		pista = new Pista();
		pista.setNombre("Tenis Peros Frescos");	
		pista.setApertura(LocalTime.parse("08:00"));
		pista.setCierre(LocalTime.parse("20:00"));
		pista.setPrecio(9.0);
		pistas.add(pista);
		
		
		
		logger.info("Cargando pistas: {}", pistas);
		repository.saveAll(pistas);
	}
}
