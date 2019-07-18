package com.ejercicio.reserpadel.services;

import java.util.List;

import com.ejercicio.reserpadel.domain.Persona;

public interface PersonaService {

	List<Persona> getAll();

	Persona findById(String dni);

	Persona create(Persona alumno);

	Persona delete(String email);

	Persona update(String email, Persona persona);

}
