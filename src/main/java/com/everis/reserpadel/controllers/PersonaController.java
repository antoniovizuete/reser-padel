package com.everis.reserpadel.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.everis.reserpadel.domain.Persona;
import com.everis.reserpadel.services.PersonaService;

@RestController
@RequestMapping("/personas")
public class PersonaController {
	
	private PersonaService service;

	public PersonaController(PersonaService service) {
		this.service = service;
	}
	
	@GetMapping
	public List<Persona> findAll(){
		return service.findAll();
	}
	
	@GetMapping("/{email}")
	public Persona findById(@PathVariable String email){
		return service.findById(email);
	}
	
	@PostMapping
	public Persona create(@RequestBody Persona persona) {
		return service.create(persona);
	}
	
	@DeleteMapping("/{email}")
	public Persona delete(@PathVariable String email) {
		return service.delete(email);
	}
	
	@PutMapping("/{email}")
	public Persona update(@PathVariable String email, @RequestBody Persona personaDto) {
		return service.update(email, personaDto);
	}
	
	
	
}
