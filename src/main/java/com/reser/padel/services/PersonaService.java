package com.reser.padel.services;

import java.util.List;

import com.reser.padel.domain.Persona;

public interface PersonaService {

	Persona create(Persona persona);

	List<Persona> findAll();

	Persona findById(String email);

	Persona update(String email, Persona persona);

	Persona delete(String email);
}
