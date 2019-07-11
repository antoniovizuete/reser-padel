package com.everis.reserpadel.services;

import java.util.List;

import com.everis.reserpadel.domain.Reserva;
import com.everis.reserpadel.repositories.ReservaRepository;

public class ReservaServiceImpl implements ReservaService {
	
	private ReservaRepository repository;

	public ReservaServiceImpl(ReservaRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public List<Reserva> findAll() {
		return repository.findAll();
	}

	@Override
	public Reserva findById(Integer id) {
		return repository.findById(id).orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
	}

	@Override
	public Reserva create(Reserva reserva) {
		repository.findById(reserva.getId()).ifPresent(a -> {
			throw new RuntimeException("Reserva " + a.getId() + " ya existente");
		});
		return repository.save(reserva);
	}

	@Override
	public Reserva delete(Integer id) {
		Reserva reserva = findById(id);
		repository.delete(reserva);
		return  reserva;
	}

	@Override
	public Reserva update(Integer id, Reserva reservaDto) {
		Reserva reserva = findById(id);
		reserva.setFecha(reservaDto.getFecha());
		reserva.setPagada(reservaDto.getPagada());
		reserva.setPersona(reservaDto.getPersona());
		reserva.setPista(reservaDto.getPista());
		return repository.save(reserva);
	}

}
