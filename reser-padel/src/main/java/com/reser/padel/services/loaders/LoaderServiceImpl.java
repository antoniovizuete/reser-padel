package com.reser.padel.services.loaders;

import org.springframework.stereotype.Service;

@Service
public class LoaderServiceImpl implements LoaderService {

	private PersonasLoader personasLoader;
	private PistasLoader pistasLoader;
	private ReservasLoader reservasLoader;
	
	public LoaderServiceImpl(PersonasLoader personasLoader, PistasLoader pistasLoader, ReservasLoader reservasLoader) {
		super();
		this.personasLoader = personasLoader;
		this.pistasLoader = pistasLoader;
		this.reservasLoader = reservasLoader;
	}
	
	@Override
	public void load() {
		personasLoader.load();
		pistasLoader.load();
		reservasLoader.load();
	}

}
