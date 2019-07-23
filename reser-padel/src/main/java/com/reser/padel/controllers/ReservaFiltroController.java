package com.reser.padel.controllers;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.persistence.Id;

import com.reser.padel.business.Business;
import com.reser.padel.controllers.data.FechaPista;
import com.reser.padel.domain.Pista;
import com.reser.padel.domain.Reserva;
import com.reser.padel.services.ReservaService;

@RestController
@RequestMapping("/reserva/filtro")
public class ReservaFiltroController {

	private ReservaService service;
	private Business business;

	public ReservaFiltroController(ReservaService service, Business business) {
		this.service = service;
		this.business = business;
	}



	@GetMapping
	public Reserva findByFechaPista(@RequestBody FechaPista fechaPista) {
		if(fechaPista.getFecha() == null) {
			throw new RuntimeException("Fecha erronea.");
		}
		if(fechaPista.getIdPista() == null) {
			throw new RuntimeException("Id de Pista erroneo.");
		}
		
		if(!business.validarHoraEnPunto(fechaPista.getFecha())) {
			fechaPista.colocarFechaEnPunto();
		}
		
		Reserva reserva = service.findIdByFechaPista(fechaPista.getFecha(), fechaPista.getIdPista());
		if(reserva == null) {
			throw new RuntimeException("Pista libre para la fecha y hora indicadas.");
		} else {
			return reserva;
		}
	}

}

