package com.reser.padel.services.loader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("!test")
public class LoaderServiceImpl implements LoaderService {

	private PersonasLoader personasLoader;
	private PistasLoader pistasLoader;
	private ReservasLoader reservasLoader;
	
	@Value("${reserpadel.carga.inicial}")
	private boolean cargaInicial;
		
	public LoaderServiceImpl(PersonasLoader personasLoader, PistasLoader pistasLoader, ReservasLoader reservasLoader) {
		super();
		this.personasLoader = personasLoader;
		this.pistasLoader = pistasLoader;
		this.reservasLoader = reservasLoader;
	}

	@Override
	public void load() {
		if (cargaInicial) {
			personasLoader.cargaInicial();
			pistasLoader.cargaInicial();
			reservasLoader.cargaInicial();
		}
	}

}
