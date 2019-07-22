package com.everis.cestevez.padel.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.everis.cestevez.padel.domain.Persona;
import com.everis.cestevez.padel.services.PersonaService;

@RestController
@RequestMapping("/personas")
public class PersonasController {
	
	private PersonaService personaServ;
	
	public PersonasController(PersonaService personaServ){
		this.personaServ=personaServ;
	}
	
	@PostMapping
	public Persona create(@RequestBody Persona persona) {
		return personaServ.create(persona);		
	}
	
	@GetMapping
	public List<Persona> findAll() {
		return personaServ.findAll();
	}

}
