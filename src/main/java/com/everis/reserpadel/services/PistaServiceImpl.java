package com.everis.reserpadel.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.everis.reserpadel.domain.Pista;
import com.everis.reserpadel.repositories.PistaRepository;

@Service
public class PistaServiceImpl implements PistaService {

	private PistaRepository repository;

	public PistaServiceImpl(PistaRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Pista> findAll() {
		return repository.findAll();
	}

	@Override
	public Pista findById(Integer id) {
		return repository.findById(id).orElseThrow(() -> new RuntimeException("Pista no encontrada"));
	}

	@Override
	public Pista create(Pista pista) {
		repository.findById(pista.getId()).ifPresent(a ->{ 		
		throw new RuntimeException("Pista " + a.getId() + "ya existe");
	});
		return repository.save(pista);
	}

	@Override
	public Pista delete(Integer id) {
		Pista pista = findById(id);
		repository.delete(pista);
		return pista;
	}

	@Override
	public Pista update(Integer id, Pista pistaDto) {
		Pista pista = findById(id);
		pista.setApertura(pistaDto.getApertura());
		pista.setCierre(pistaDto.getCierre());
		pista.setNombre(pistaDto.getNombre());
		pista.setPrecio(pistaDto.getPrecio());
		return repository.save(pista);
	}
	
	
	
	
	
}
