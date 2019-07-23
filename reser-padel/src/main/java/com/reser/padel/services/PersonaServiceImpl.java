package com.reser.padel.services;

import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reser.padel.domain.Persona;
import com.reser.padel.repositories.PersonaRepository;

@Service
@Transactional
public class PersonaServiceImpl implements PersonaService {

	private PersonaRepository repository;

	public PersonaServiceImpl(PersonaRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public List<Persona> findAll() {
		return repository.findAll();
	}

	@Override
	public Persona findById(String email) {
		return repository.findById(email)
				.orElseThrow(() -> new RuntimeException("Persona no encontrada"));
	}

	@Override
	public Persona create(Persona persona) {
		repository.findById(persona.getEmail()).ifPresent(a -> {
			throw new RuntimeException("Persona " + a.getEmail() + " ya existente");
		});
		return repository.save(persona);
	}

	@Override
	public Persona delete(String email) {
		Persona persona = findById(email);
		repository.delete(persona);
		return persona;
	}
	
	@Override
	public Persona update(String email, Persona personaDto) {
		Persona persona = findById(email);
		persona.setNombre(personaDto.getNombre());
		persona.setNivelPadel(personaDto.getNivelPadel());
		return repository.save(persona);
	}

}
