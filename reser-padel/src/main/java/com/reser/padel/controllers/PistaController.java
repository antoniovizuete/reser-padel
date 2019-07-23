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

import com.reser.padel.domain.Pista;
import com.reser.padel.services.PistaService;


@RestController
@RequestMapping("/pista")

public class PistaController {

	private PistaService service;

	public PistaController(PistaService service) {
		this.service = service;
	}

	@GetMapping
	public List<Pista> findAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public Pista findById(@PathVariable Integer id) {
		return service.findById(id);
	}

	@PostMapping
	public Pista create(@RequestBody Pista pista) {
		return service.create(pista);
	}

	@DeleteMapping("/{id}")
	public Pista delete(@PathVariable Integer id) {
		return service.delete(id);
	}
	
	@PutMapping("/{id}")
	public Pista update(@PathVariable Integer id, @RequestBody Pista pistaDto) {
		return service.update(id, pistaDto);
	}

}
