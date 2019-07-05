package com.reser.padel.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "PERSONAS")
public class Persona {
	
	@Id
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "NOMBRE")
	private String nombre;
	
	@Column(name = "NIVEL_PADEL")
	private Integer nivelPadel;
	
}
