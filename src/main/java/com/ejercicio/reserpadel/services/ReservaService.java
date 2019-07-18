package com.ejercicio.reserpadel.services;

import java.util.List;

import com.ejercicio.reserpadel.domain.Reserva;

public interface ReservaService {

	List<Reserva> getAll();

	Reserva findById(String id);

	Reserva create(Reserva reserva);

	Reserva delete(String id);

	Reserva update(String id, Reserva reserva);

}
