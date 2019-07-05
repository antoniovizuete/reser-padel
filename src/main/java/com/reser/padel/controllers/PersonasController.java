package com.reser.padel.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reser.padel.domain.Persona;
import com.reser.padel.services.PersonaServiceImpl;

@RestController
@RequestMapping("/personas")
public class PersonasController {

	PersonaServiceImpl personaServiceImpl;

	public PersonasController(PersonaServiceImpl personaServiceImpl) {
		super();
		this.personaServiceImpl = personaServiceImpl;
	}
	
	@PostMapping
	public Persona create(@RequestBody Persona persona) {
		return personaServiceImpl.create(persona);
	}

	@GetMapping
	public List<Persona> findAll() {
		return personaServiceImpl.findAll();
	}
	
	@GetMapping("/{email}")
	public Persona findById(@PathVariable String email) {
		return personaServiceImpl.findById(email);
	}
	
	@PutMapping("/{email}")
	public Persona update(@PathVariable String email, @RequestBody Persona personaDTO) {
		return personaServiceImpl.update(email, personaDTO);
	}
	
	@DeleteMapping("/{email}")
	public Persona delete(@PathVariable String email) {
		return personaServiceImpl.delete(email);
	}

}
