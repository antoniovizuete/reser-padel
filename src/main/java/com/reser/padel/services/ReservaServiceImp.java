package com.reser.padel.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.reser.padel.domain.Reserva;
import com.reser.padel.repositories.ReservaRepository;

@Service
public class ReservaServiceImp implements ReservaService {

	ReservaRepository reservaRepository;
		
	public ReservaServiceImp(ReservaRepository reservaRepository) {
		super();
		this.reservaRepository = reservaRepository;
	}

	@Override
	public Reserva create(Reserva reserva) {
		reservaRepository.findById(reserva.getId()).ifPresent(t -> {throw new RuntimeException("Reserva " + t.getId() + " ya existe");});
		
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

}
