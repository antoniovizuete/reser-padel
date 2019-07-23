package com.reser.padel.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.reser.padel.domain.Pista;
import com.reser.padel.domain.Reserva;

@Service
public interface ReservaService {


	List<Reserva> findAll();

	Reserva findById(Integer id);

	Reserva create(Reserva reserva);

	Reserva delete(Integer id);

	Reserva update(Integer id, Reserva reserva);
	
	Reserva findIdByFechaPista(LocalDateTime fecha, Integer idPista);

}
