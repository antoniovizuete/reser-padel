package com.everis.reserpadel.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

// CRUD:
//  - CREATE: POST
//  - READ: GET
//    - All
//    - 1
//  - UPDATE: PUT
//  - DELETE: DELETE

@Entity
public class Reserva {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne
	private Persona persona;
	
	@OneToOne
	private Pista pista;
	
	private LocalDateTime fecha;
	private Boolean pagada;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public Pista getPista() {
		return pista;
	}
	public void setPista(Pista pista) {
		this.pista = pista;
	}
	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	public Boolean getPagada() {
		return pagada;
	}
	public void setPagada(Boolean pagada) {
		this.pagada = pagada;
	}
	
	
}
