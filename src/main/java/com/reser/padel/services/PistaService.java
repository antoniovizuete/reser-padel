package com.reser.padel.services;

import java.util.List;

import com.reser.padel.domain.Pista;

public interface PistaService {

	Pista create(Pista pista);

	List<Pista> findAll();

	Pista findById(Integer id);

	Pista update(Integer id, Pista pista);

	Pista delete(Integer id);
	
}
