package com.reser.padel.services;

import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.reser.padel.domain.Pista;
import com.reser.padel.domain.Reserva;
import com.reser.padel.repositories.ReservaRepository;

@Service
public class ReservaServiceImp implements ReservaService {

	ReservaRepository reservaRepository;
	PersonaServiceImpl personaService;
	PistaServiceImpl pistaService;
		
	public ReservaServiceImp(ReservaRepository reservaRepository, 
							 PersonaServiceImpl personaService,
			                 PistaServiceImpl pistaService) {
		super();
		this.reservaRepository = reservaRepository;
		this.personaService = personaService;
		this.pistaService = pistaService;
	}

	@Override
	public Reserva create(Reserva reserva) {
		
		personaService.findById(reserva.getPersona().getEmail());
		validarHorario(reserva.getFecha().toLocalTime(), reserva.getPista());

		return reservaRepository.save(reserva);
	}

	@Override
	public List<Reserva> findAll() {
		return reservaRepository.findAll();
	}

	@Override
	public Reserva findById(Integer id) {
		return reservaRepository.findById(id).orElseThrow(() -> new RuntimeException("Reserva " + id + " no encontrada"));
	}

	@Override
	public Reserva update(Integer id, Reserva reserva) {
		Reserva reservaUpdate = findById(id);
		
		personaService.findById(reserva.getPersona().getEmail());
		validarHorario(reserva.getFecha().toLocalTime(), reserva.getPista());
		
		reservaUpdate.setPersona(reserva.getPersona());
		reservaUpdate.setPista(reserva.getPista());
		reservaUpdate.setFecha(reserva.getFecha());
		reservaUpdate.setPagada(reserva.getPagada());
		
		return reservaRepository.save(reservaUpdate);
	}

	@Override
	public Reserva delete(Integer id) {
		Reserva reserva = findById(id);
		reservaRepository.delete(reserva);

		return reserva;
	}
	
	private void validarHorario(LocalTime hora, Pista pista) {
		
		Pista pistaReservada = pistaService.findById(pista.getId());
		
		LocalTime apertura = pistaReservada.getApertura();
		LocalTime cierre = pistaReservada.getCierre();
		
		if (hora.isBefore(apertura) || hora.isAfter(cierre.minusHours(1))) {
			throw new RuntimeException("La reserva está fuera del horario de la pista.");
		}

	}

}
