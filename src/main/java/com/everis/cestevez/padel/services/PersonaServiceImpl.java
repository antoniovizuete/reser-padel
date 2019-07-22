package com.everis.cestevez.padel.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.everis.cestevez.padel.domain.Persona;
import com.everis.cestevez.padel.repositories.PersonaRepository;

@Service
@Transactional
public class PersonaServiceImpl implements PersonaService {
	
	private PersonaRepository persRepository;
	
	public PersonaServiceImpl(PersonaRepository persRepository) {
		this.persRepository=persRepository;		
	}

	public List<Persona> findAll() {
		return persRepository.findAll();
	}

	public Persona findById(String email) {
		return persRepository.findById(email)
				.orElseThrow(() -> new RuntimeException("Persona no encontrada"));
	}

	public Persona create(Persona persona) {
		persRepository.findById(persona.getEmail()).ifPresent(a -> {
			throw new RuntimeException("Persona " + a.getEmail() + " ya existente");});
		return persRepository.save(persona);
	}

	public Persona delete(String dni) {
		return null;
	}

	public Persona update(String dni, Persona alumnoDto) {
		return null;
	}

}
