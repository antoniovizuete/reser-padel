package com.reser.padel.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.reser.padel.domain.Pista;
import com.reser.padel.repositories.PistaRepository;

@Service
public class PistaServiceImpl implements PistaService {

	private PistaRepository pistaRepository;
		
	public PistaServiceImpl(PistaRepository pistaRepository) {
		super();
		this.pistaRepository = pistaRepository;
	}

	@Override
	public Pista create(Pista pista) {
		pistaRepository.findById(pista.getId()).ifPresent(t -> {throw new RuntimeException("Pista " + t.getId() + " ya existe");});
		
		return pistaRepository.save(pista);
	}

	@Override
	public List<Pista> findAll() {
		return pistaRepository.findAll();
	}

	@Override
	public Pista findById(Integer id) {
		return pistaRepository.findById(id).orElseThrow(() -> new RuntimeException("Pista " + id + " no encontrada"));
	}

	@Override
	public Pista update(Integer id, Pista pista) {
		Pista pistaUpdate = findById(id);
		
		pistaUpdate.setNombre(pista.getNombre());
		pistaUpdate.setApertura(pista.getApertura());
		pistaUpdate.setCierre(pista.getCierre());
		pistaUpdate.setPrecio(pista.getPrecio());
		
		return pistaRepository.save(pistaUpdate);
	}

	@Override
	public Pista delete(Integer id) {
		Pista pista = findById(id);
		pistaRepository.delete(pista);
		
		return pista;
	}

}
