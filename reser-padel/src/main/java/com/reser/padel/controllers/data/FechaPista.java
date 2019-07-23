package com.reser.padel.controllers.data;

import java.time.LocalDateTime;

import com.reser.padel.domain.Pista;

public class FechaPista {

	private LocalDateTime fecha;
	private Integer idPista;

	public Integer getIdPista() {
		return idPista;
	}

	public void setIdPista(Integer idPista) {
		this.idPista = idPista;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public void colocarFechaEnPunto() {
        setFecha(this.fecha.minusMinutes(this.fecha.getMinute()));
        setFecha(this.fecha.minusSeconds(this.fecha.getSecond()));
        setFecha(this.fecha.minusNanos(this.fecha.getNano()));
	}

}
