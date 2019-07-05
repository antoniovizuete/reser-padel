package com.reser.padel.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.reser.padel.domain.Persona;
import com.reser.padel.repositories.PersonaRepository;

@Service
public class PersonaServiceImpl implements PersonaService {

	private PersonaRepository personaRepository;
	
	public PersonaServiceImpl(PersonaRepository personaRepository) {
		super();
		this.personaRepository = personaRepository;
	}

	@Override
	public Persona create(Persona persona) {
		personaRepository.findById(persona.getEmail()).ifPresent(t -> {throw new RuntimeException("Persona " + t.getEmail() + " ya existe");});
		
		return personaRepository.save(persona);
	}

	@Override
	public List<Persona> findAll() {
		return personaRepository.findAll();
	}

	@Override
	public Persona findById(String email) {
		return personaRepository.findById(email).orElseThrow(() -> new RuntimeException("Persona " + email + " no encontrada")); 
	}

	@Override
	public Persona update(String email, Persona persona) {
		Persona personaUpdate = findById(email);
		
		personaUpdate.setNombre(persona.getNombre());
		personaUpdate.setNivelPadel(persona.getNivelPadel());
		
		return personaRepository.save(personaUpdate);
	}

	@Override
	public Persona delete(String email) {
		Persona persona = findById(email);
		personaRepository.delete(persona);

		return persona;
	}

}
