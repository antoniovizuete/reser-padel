package com.reser.padel.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reser.padel.business.Business;
import com.reser.padel.domain.Pista;
import com.reser.padel.repositories.PistaRepository;

@Service
@Transactional
public class PistaServiceImpl implements PistaService {

	private PistaRepository repository;

	public PistaServiceImpl(PistaRepository repository, Business business) {
		this.repository = repository;
	}
	
	@Override
	public List<Pista> findAll() {
		return repository.findAll();
	}

	@Override
	public Pista findById(Integer id) {
		return repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Pista no encontrada"));
	}

	@Override
	public Pista create(Pista pista) {
		repository.findById(pista.getId()).ifPresent(a -> {
			throw new RuntimeException("Pista " + a.getId() + " ya existente");
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
		pista.setNombre(pistaDto.getNombre());
		pista.setApertura(pistaDto.getApertura());
		pista.setCierre(pistaDto.getCierre());
		pista.setPrecio(pistaDto.getPrecio());
		return repository.save(pista);
	}
	
	@Override
	public List<Integer> findIdByNombre(String nombre) {
		return repository.findIdByNombre(nombre);
	}

}
