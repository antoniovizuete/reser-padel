package com.reser.padel.services;

import java.time.LocalTime;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.reser.padel.domain.Pista;
import com.reser.padel.domain.Reserva;
import com.reser.padel.repositories.ReservaRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
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
		Reserva retorno = null;
		
		personaService.findById(reserva.getPersona().getEmail());
		validarHorario(reserva.getFecha().toLocalTime(), reserva.getPista());
		try {
			retorno = reservaRepository.save(reserva);
		} catch (DataIntegrityViolationException e) {
			throw new RuntimeException("Ya existe una reserva para la pista " + reserva.getPista().getNombre() 
					                 + " en la fecha " + reserva.getFecha().toLocalDate()
			                         + " y la hora " + reserva.getFecha().toLocalTime());
		}

		return retorno;
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
		Reserva retorno = null;
		Reserva reservaUpdate = findById(id);
		
		personaService.findById(reserva.getPersona().getEmail());
		validarHorario(reserva.getFecha().toLocalTime(), reserva.getPista());
		
		reservaUpdate.setPersona(reserva.getPersona());
		reservaUpdate.setPista(reserva.getPista());
		reservaUpdate.setFecha(reserva.getFecha());
		reservaUpdate.setPagada(reserva.getPagada());
		
		try {
			retorno = reservaRepository.save(reservaUpdate);
		} catch (DataIntegrityViolationException e) {
			throw new RuntimeException("Ya existe una reserva para la pista " + reserva.getPista().getNombre() 
	                 + " en la fecha " + reserva.getFecha().toLocalDate()
                     + " y la hora " + reserva.getFecha().toLocalTime());
		}

		return retorno;
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
		
		if (apertura == null) {
			apertura = LocalTime.of(8, 00);
			log.info("Hora de apertura por defecto de la pista: 08:00");
		}
		
		if (cierre == null) {
			cierre = LocalTime.of(22, 00);
			log.info("Hora de cierre por defecto de la pista: 22:00");
		}
		
		if (hora.isBefore(apertura) || hora.isAfter(cierre.minusHours(1))) {
			throw new RuntimeException("La reserva está fuera del horario de la pista.");
		}

	}
	
}
