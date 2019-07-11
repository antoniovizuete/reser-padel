package com.everis.reserpadel.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Persona {
	@Id
	private String email;
	private String nombre;
	private Integer nivelPadel;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getNivelPadel() {
		return nivelPadel;
	}

	public void setNivelPadel(Integer nivelPadel) {
		this.nivelPadel = nivelPadel;
	}

}
