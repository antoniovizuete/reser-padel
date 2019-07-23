package com.reser.padel.services;

import java.util.List;

import com.reser.padel.domain.Persona;


public interface PersonaService {

	List<Persona> findAll();

	Persona findById(String email);

	Persona create(Persona persona);

	Persona delete(String email);

	Persona update(String email, Persona persona);

}
