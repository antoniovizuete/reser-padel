package com.everis.reserpadel.services;

import java.util.List;

import com.everis.reserpadel.domain.Pista;

public interface PistaService {
	
	List<Pista> findAll();
	
	Pista findById(Integer id);
	
	Pista create(Pista pista);
	
	Pista delete(Integer id);
	
	Pista update(Integer id, Pista pistaDto);
	

}
