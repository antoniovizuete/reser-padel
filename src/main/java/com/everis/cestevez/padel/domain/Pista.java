package com.everis.cestevez.padel.domain;
import java.time.LocalTime;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pistas")
public class Pista {
	@Getter	@Setter	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Integer id;
	@Getter	@Setter	private String nombre;
	@Getter	@Setter	private LocalTime apertura;
	@Getter	@Setter	private LocalTime cierre;
	@Getter	@Setter	private Double precio;

}
