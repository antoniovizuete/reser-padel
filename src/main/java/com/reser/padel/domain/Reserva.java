package com.reser.padel.domain;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
@Table(name = "RESERVAS", uniqueConstraints={@UniqueConstraint(columnNames={"PISTA", "FECHA"})})
public class Reserva {
  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
  
	@OneToOne
	@JoinColumn(name = "PERSONA", nullable = false)
	@NotNull(message = "Se debe informar una persona en la reserva")
	private Persona persona;
  
	@OneToOne
	@JoinColumn(name = "PISTA", nullable = false)
	@NotNull(message = "Se debe informar una pista en la reserva")
	private Pista pista;
  
	@Column(name = "FECHA", nullable = false)
	@NotNull(message = "Se debe informar una fecha y hora de reserva")
	@FutureOrPresent(message = "La fecha de reserva debe ser a partir de hoy")
	private LocalDateTime fecha;
  
	@Column(name = "PAGADA", nullable = false)
	@NotNull(message = "Se debe informar si está pagada la reserva o no")
	private Boolean pagada;
	
}
