package com.ejercicio.reserpadel.services;

import java.util.List;

import com.ejercicio.reserpadel.domain.Pista;

public interface PistaService {

	List<Pista> getAll();

	Pista findById(String id);

	Pista create(Pista pista);

	Pista delete(String id);

	Pista update(String email, Pista persona);

}
