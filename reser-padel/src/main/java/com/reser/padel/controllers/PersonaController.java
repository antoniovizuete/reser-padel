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
import com.reser.padel.services.PersonaService;


@RestController
@RequestMapping("/persona")
public class PersonaController {

	private PersonaService service;

	public PersonaController(PersonaService service) {
		this.service = service;
	}

	@GetMapping
	public List<Persona> findAll() {
		return service.findAll();
	}

	@GetMapping("/{email}")
	public Persona findById(@PathVariable String email) {
		return service.findById(email);
	}

	@PostMapping
	public Persona create(@RequestBody Persona Persona) {
		return service.create(Persona);
	}

	@DeleteMapping("/{email}")
	public Persona delete(@PathVariable String email) {
		return service.delete(email);
	}
	
	@PutMapping("/{email}")
	public Persona update(@PathVariable String email, @RequestBody Persona PersonaDto) {
		return service.update(email, PersonaDto);
	}

}
