package com.reser.padel.controllers;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reser.padel.domain.Reserva;
import com.reser.padel.services.ReservaServiceImp;

@RestController
@RequestMapping("/reservas")
@Validated
public class ReservasController {

	private ReservaServiceImp reservaServiceImpl;

	public ReservasController(ReservaServiceImp reservaServiceImpl) {
		super();
		this.reservaServiceImpl = reservaServiceImpl;
	}
	
	@PostMapping
	public Reserva create(@RequestBody @Valid Reserva reserva, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
            throw new RuntimeException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
		
		return reservaServiceImpl.create(reserva);
	}

	@GetMapping
	public List<Reserva> findAll() {
		return reservaServiceImpl.findAll();
	}
	
	@GetMapping("/{id}")
	public Reserva findById(@PathVariable @Positive Integer id) {
		return reservaServiceImpl.findById(id);
	}
	
	@PutMapping("/{id}")
	public Reserva update(@PathVariable @Positive Integer id, @RequestBody @Valid Reserva reservaDTO, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
            throw new RuntimeException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
		
		return reservaServiceImpl.update(id, reservaDTO);
	}
	
	@DeleteMapping("/{id}")
	public Reserva delete(@PathVariable @Positive Integer id) {
		return reservaServiceImpl.delete(id);
	}
}
