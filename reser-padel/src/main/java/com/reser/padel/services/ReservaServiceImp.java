package com.reser.padel.services;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.reser.padel.business.Business;
import com.reser.padel.domain.Pista;
import com.reser.padel.domain.Reserva;
import com.reser.padel.repositories.ReservaRepository;

@Service
@Transactional
public class ReservaServiceImp implements ReservaService {
	
	private ReservaRepository repository;
	private Business business;

	public ReservaServiceImp(ReservaRepository repository, Business business) {
		this.repository = repository;
		this.business = business;
	}
	
	@Override
	public List<Reserva> findAll() {
		return repository.findAll();
	}

	@Override
	public Reserva findById(Integer id) {
		return repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
	}

	@Override
	public Reserva create(Reserva reserva) {
		repository.findById(reserva.getId()).ifPresent(a -> {
			throw new RuntimeException("Reserva " + a.getId() + " ya existente");
		});
		if(business.validarReserva(reserva, repository)) {
			return repository.save(reserva);
		} else {
			return null;
		}
	}

	@Override
	public Reserva delete(Integer id) {
		Reserva reserva = findById(id);
		repository.delete(reserva);
		return reserva;
	}
	
	@Override
	public Reserva update(Integer id, Reserva reservaDto) {
		Reserva reserva = findById(id);
		reserva.setPersona(reservaDto.getPersona());
		reserva.setPista(reservaDto.getPista());
		reserva.setFecha(reservaDto.getFecha());
		reserva.setPagada(reservaDto.isPagada());
		if(business.validarReserva(reserva, repository)) {
			return repository.save(reserva);
		} else {
			return null;
		}
	}

	@Override
	public Reserva findIdByFechaPista(LocalDateTime fecha, Integer idPista) {
		List<Integer> lista = repository.findIdByFechaPista(fecha, idPista);
		
		if(lista.size() > 0) {
			return findById(lista.get(0));
		} else {
			return null;
		}
	}
}
