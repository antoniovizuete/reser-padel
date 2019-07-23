package com.reser.padel.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
public class Persona {
	
	@Id
	@Email
	private String email;
	@NotNull
	private String nombre;
	@NotNull
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
