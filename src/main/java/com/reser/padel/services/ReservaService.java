package com.reser.padel.services;

import java.util.List;

import com.reser.padel.domain.Reserva;

public interface ReservaService {

	Reserva create(Reserva reserva);

	List<Reserva> findAll();

	Reserva findById(Integer id);

	Reserva update(Integer id, Reserva reserva);

	Reserva delete(Integer id);
	
}
