package com.everis.reserpadel.services;

import java.util.List;

import com.everis.reserpadel.domain.Reserva;

public interface ReservaService {
	
	List<Reserva> findAll();
	
	Reserva findById(Integer id);
	
	Reserva create(Reserva pista);
	
	Reserva delete(Integer id);
	
	Reserva update(Integer id, Reserva pistaDto);
	

}
