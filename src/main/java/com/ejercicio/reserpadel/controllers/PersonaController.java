package com.ejercicio.reserpadel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejercicio.reserpadel.services.PersonaService;

@RestController
@RequestMapping("/Persona")
public class PersonaController {
	private PersonaService service;


	public PersonaController(PersonaService service) {
		this.service = service;
	}
	
	
	
}
