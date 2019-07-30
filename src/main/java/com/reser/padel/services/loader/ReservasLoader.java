package com.reser.padel.services.loader;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.reser.padel.domain.Persona;
import com.reser.padel.domain.Pista;
import com.reser.padel.domain.Reserva;
import com.reser.padel.repositories.PersonaRepository;
import com.reser.padel.repositories.PistaRepository;
import com.reser.padel.repositories.ReservaRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Profile("!test")
public class ReservasLoader {

	private ReservaRepository reservaRepository;

	@Autowired
	private PersonaRepository personaRepository;
	
	@Autowired
	private PistaRepository pistaRepository;

	public ReservasLoader(ReservaRepository reservaRepository) {
		super();
		this.reservaRepository = reservaRepository;
	}
	
	public void cargaInicial() {
		
		if (reservaRepository.count() > 0) {
			log.info("Ya existen reservas en la tabla.");
			return;
		}
		
		if (personaRepository.count() == 0) {
			log.info("No existen personas para asignarles reservas.");
			return;
		}

		if (pistaRepository.count() == 0) {
			log.info("No existen pistas para asignarles reservas.");
			return;
		}
		
		Persona persona = null;
		Pista pista = null;
		LocalDateTime fecha = null;
		
		persona = personaRepository.findAll().get(0);
		pista = pistaRepository.findAll().get(0);
		fecha = LocalDateTime.of(2019, 9, 19, pistaRepository.findAll().get(0).getApertura().minusHours(-1).getHour(), 00);
		grabarReserva(persona, pista, fecha, false);
		
		persona = personaRepository.findAll().get(1);
		pista = pistaRepository.findAll().get(1);
		fecha = LocalDateTime.of(2019, 9, 20, pistaRepository.findAll().get(1).getApertura().minusHours(-1).getHour(), 00);
		grabarReserva(persona, pista, fecha, true);
		
		persona = personaRepository.findAll().get(2);
		pista = pistaRepository.findAll().get(2);
		fecha = LocalDateTime.of(2019, 9, 21, pistaRepository.findAll().get(2).getApertura().minusHours(-1).getHour(), 00);
		grabarReserva(persona, pista, fecha, true);
		
		log.info("Tabla 'RESERVAS' cargada correctamente!");
		
	}
	
	private void grabarReserva(Persona persona, Pista pista, LocalDateTime fecha, boolean pagada) {
		Reserva reserva = new Reserva();
		
		reserva.setPersona(persona);
		reserva.setPista(pista);
		reserva.setFecha(fecha);
		reserva.setPagada(pagada);
		reservaRepository.save(reserva);
	}
}
