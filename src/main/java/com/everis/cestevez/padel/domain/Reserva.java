package com.everis.cestevez.padel.domain;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "reservas")
public class Reserva {
	@Getter	@Setter	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Getter	@Setter @OneToOne private Persona persona;
	@Getter	@Setter @OneToOne private Pista pista;
	@Getter	@Setter private LocalDateTime fecha;
	@Getter	@Setter private Boolean pagada;

}

