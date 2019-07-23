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

import com.reser.padel.domain.Reserva;
import com.reser.padel.services.ReservaService;


@RestController
@RequestMapping("/reserva")

public class ReservaController {

	private ReservaService service;

	public ReservaController(ReservaService service) {
		this.service = service;
	}

	@GetMapping
	public List<Reserva> findAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public Reserva findById(@PathVariable Integer id) {
		return service.findById(id);
	}

	@PostMapping
	public Reserva create(@RequestBody Reserva reserva) {
		return service.create(reserva);
	}

	@DeleteMapping("/{id}")
	public Reserva delete(@PathVariable Integer id) {
		return service.delete(id);
	}
	
	@PutMapping("/{id}")
	public Reserva update(@PathVariable Integer id, @RequestBody Reserva reservaDto) {
		return service.update(id, reservaDto);
	}

}

