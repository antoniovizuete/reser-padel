package com.reser.padel.business;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.reser.padel.domain.Reserva;
import com.reser.padel.repositories.ReservaRepository;

@Component
public class Business {
	
	public boolean validarReserva(Reserva reserva, ReservaRepository reservaRepository) {
		if(reserva.getPersona() == null) {
			throw new RuntimeException("Persona no valida.");
		}
		
		if(reserva.getPista() == null) {
			throw new RuntimeException("Pista no valida.");
		}
		
		if(reserva.getFecha().toLocalTime().isAfter(reserva.getPista().getCierre()) ||
		   reserva.getFecha().toLocalTime().isBefore(reserva.getPista().getApertura())) {
			throw new RuntimeException("Reserva fuera de horario.");
		}
		
		if(reservaRepository.findIdByFechaPista(reserva.getFecha(), reserva.getPista().getId()).size() > 0) {
			throw new RuntimeException("Pista ya reservada.");
		}
		
		if(reserva.getFecha().isBefore(LocalDateTime.now())) {
		   throw new RuntimeException("No se puede reservar una pista en el pasado.");
		}
		
		if(!validarHoraEnPunto(reserva.getFecha())) {
    	   throw new RuntimeException("La hora de inicio de la reserva debe ser una hora en punto.");
		};
		
		return true;
	}
	
	public boolean validarHoraEnPunto(LocalDateTime localDateTime) {

		if(localDateTime.getMinute() != 0 ||
		   localDateTime.getSecond() != 0 ||
		   localDateTime.getNano() != 0) {
			return false;
		}
		
		return true;
	}
}
