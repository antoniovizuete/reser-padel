package com.everis.reserpadel.services;

import java.util.List;

import com.everis.reserpadel.domain.Persona;

public interface PersonaService {
	
	List<Persona> findAll();
	
	Persona findById(String email);
	
	Persona create(Persona persona);
	
	Persona delete(String email);
	
	Persona update(String dni, Persona personaDto);
	

}
