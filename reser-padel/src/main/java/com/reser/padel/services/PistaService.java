package com.reser.padel.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.reser.padel.domain.Pista;


@Service
public interface PistaService {

	List<Pista> findAll();

	Pista findById(Integer id);

	Pista create(Pista pista);

	Pista delete(Integer id);

	Pista update(Integer id, Pista pista);
	
	List<Integer> findIdByNombre(String nombre);

}
