package com.reser.padel.domain;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "PISTAS")
public class Pista {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
  
	@Column(name = "NOMBRE")
	private String nombre;
  
	@Column(name = "APERTURA")
	private LocalTime apertura;
  
	@Column(name = "CIERRE")
	private LocalTime cierre;
  
	@Column(name = "PRECIO")
	private Double precio;
	
}
