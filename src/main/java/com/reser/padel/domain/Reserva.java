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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.Data;

@Entity
@Data
@Table(name = "RESERVAS", uniqueConstraints={@UniqueConstraint(columnNames={"PERSONA", "PISTA"})})
public class Reserva {
  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
  
	@OneToOne
	@JoinColumn(name = "PERSONA", nullable = false)
	private Persona persona;
  
	@OneToOne
	@JoinColumn(name = "PISTA", nullable = false)
	private Pista pista;
  
	@Column(name = "FECHA")
	@FutureOrPresent(message = "La fecha de reserva debe ser a partir de hoy")
	private LocalDateTime fecha;
  
	@Column(name = "PAGADA")
	@Min(value = 0, message = "El valor debe ser 0 o 1")
	@Max(value = 0, message = "El valor debe ser 0 o 1")
	private Boolean pagada;
	
}
