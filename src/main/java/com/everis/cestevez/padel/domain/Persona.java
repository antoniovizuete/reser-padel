package com.everis.cestevez.padel.domain;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "personas")
public class Persona {
	@Getter	@Setter	@Id	private String email;
	@Getter	@Setter	private String nombre;
	@Getter	@Setter	private Integer nivelPadel;
	
	/*public Persona(String email, String nombre, Integer nivelPadel) {
		this.email=email;
		this.nombre=nombre;
		this.nivelPadel=nivelPadel;
	
		
	public Persona() {
		super();
	}}*/
	
	@Override
	public String toString() {
		return this.getClass().getName()+" [email=" + email + ", nombre=" + nombre + ", nivel de padel=" + nivelPadel + "]";
	}
}


