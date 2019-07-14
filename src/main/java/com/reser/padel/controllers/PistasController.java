package com.reser.padel.controllers;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.stereotype.Controller;
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

import com.reser.padel.domain.Pista;
import com.reser.padel.services.PistaServiceImpl;

@RestController
@RequestMapping("/pistas")
@Validated
@Controller
public class PistasController {

	private PistaServiceImpl pistaServiceImpl;
		
	public PistasController(PistaServiceImpl pistaServiceImpl) {
		super();
		this.pistaServiceImpl = pistaServiceImpl;
	}

	@PostMapping
	public Pista create(@RequestBody @Valid Pista pista, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
            throw new RuntimeException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
		
		return pistaServiceImpl.create(pista);
	}

	@GetMapping
	public List<Pista> findAll() {
		return pistaServiceImpl.findAll();
	}
	
	@GetMapping("/{id}")
	public Pista findById(@PathVariable @Positive Integer id) {
		return pistaServiceImpl.findById(id);
	}
	
	@PutMapping("/{id}")
	public Pista update(@PathVariable @Positive Integer id, @RequestBody @Valid Pista pistaDTO, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
            throw new RuntimeException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
		
		return pistaServiceImpl.update(id, pistaDTO);
	}
	
	@DeleteMapping("/{id}")
	public Pista delete(@PathVariable @Positive Integer id) {
		return pistaServiceImpl.delete(id);
	}
	
}
