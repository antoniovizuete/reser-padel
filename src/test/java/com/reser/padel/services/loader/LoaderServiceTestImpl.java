package com.reser.padel.services.loader;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Profile("test")
public class LoaderServiceTestImpl implements LoaderService {

	@Override
	public void load() {
		log.warn("En pruebas...");
	}
	
}
