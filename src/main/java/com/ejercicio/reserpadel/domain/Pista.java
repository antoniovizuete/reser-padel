package com.ejercicio.reserpadel.domain;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Pista {
	@Id
	private Integer id;
	private String nombre;
	private LocalTime apertura;
	private LocalTime cierre;
	private Double precio;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalTime getApertura() {
		return apertura;
	}

	public void setApertura(LocalTime apertura) {
		this.apertura = apertura;
	}

	public LocalTime getCierre() {
		return cierre;
	}

	public void setCierre(LocalTime cierre) {
		this.cierre = cierre;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

}
