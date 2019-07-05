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
import com.reser.padel.services.PistaServiceImpl;

@RestController
@RequestMapping("/pistas")
public class PistasController {

	private PistaServiceImpl pistaServiceImpl;
		
	public PistasController(PistaServiceImpl pistaServiceImpl) {
		super();
		this.pistaServiceImpl = pistaServiceImpl;
	}

	@PostMapping
	public Pista create(@RequestBody Pista pista) {
		return pistaServiceImpl.create(pista);
	}

	@GetMapping
	public List<Pista> findAll() {
		return pistaServiceImpl.findAll();
	}
	
	@GetMapping("/{id}")
	public Pista findById(@PathVariable Integer id) {
		return pistaServiceImpl.findById(id);
	}
	
	@PutMapping("/{id}")
	public Pista update(@PathVariable Integer id, @RequestBody Pista pistaDTO) {
		return pistaServiceImpl.update(id, pistaDTO);
	}
	
	@DeleteMapping("/{id}")
	public Pista delete(@PathVariable Integer id) {
		return pistaServiceImpl.delete(id);
	}
	
}
