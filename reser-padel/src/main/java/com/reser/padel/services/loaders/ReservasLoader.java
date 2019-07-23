package com.reser.padel.services.loaders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reser.padel.domain.Reserva;
import com.reser.padel.repositories.ReservaRepository;
import com.reser.padel.services.PersonaService;
import com.reser.padel.services.PistaService;

@Service
public class ReservasLoader {

	private static Logger logger = LoggerFactory.getLogger(ReservasLoader.class);
	
	private ReservaRepository repository;
	private PersonaService personaService;
	private PistaService pistaService;

	@Autowired
	public ReservasLoader(ReservaRepository repository, PersonaService personaService, PistaService pistaService) {
		this.repository = repository;
		this.personaService = personaService;
		this.pistaService = pistaService;
	}
	
	public void load() {
		if(repository.count() > 0) {
			logger.info("Ya existen reservas.");
			return;
		}
		
		List<Reserva> reservas = new ArrayList<>();
		
		Reserva reserva = new Reserva();
		reserva.setPersona(personaService.findById("ruben.correo@everis.com"));	
		reserva.setPista(pistaService.findById(pistaService.findIdByNombre("Anis Tenis").get(0)));
		reserva.setFecha(LocalDateTime.parse("2019-07-12T12:00:00.000"));
		reserva.setPagada(true);
		reservas.add(reserva);
		
		reserva = new Reserva();
		reserva.setPersona(personaService.findById("rafa.correo@everis.com"));	
		reserva.setPista(pistaService.findById(pistaService.findIdByNombre("Tenis Peros Frescos").get(0)));
		reserva.setFecha(LocalDateTime.parse("2019-07-12T14:00:00.000"));
		reserva.setPagada(true);
		reservas.add(reserva);		
		
		reserva = new Reserva();
		reserva.setPersona(personaService.findById("rafa.correo@everis.com"));	
		reserva.setPista(pistaService.findById(pistaService.findIdByNombre("Esraque Tanopala").get(0)));
		reserva.setFecha(LocalDateTime.parse("2019-07-12T14:00:00.000"));
		reserva.setPagada(false);
		reservas.add(reserva);		
		
		
		logger.info("Cargando reservas: {}", reservas);
		repository.saveAll(reservas);
	}

}
