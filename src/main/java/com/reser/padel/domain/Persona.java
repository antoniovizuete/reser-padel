package com.reser.padel.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity
@Data
@Table(name = "PERSONAS")
public class Persona {
	
	@Id
	@Column(name = "EMAIL")
	@Email(message = "El email proporcionado no es válido")
	private String email;
	
	@Column(name = "NOMBRE")
	@NotEmpty(message = "Se debe proporcionar un nombre al usuario")
	private String nombre;
	
	@Column(name = "NIVEL_PADEL")
	@Min(value = 0, message = "El nivel no debe ser menor de 0")
	@Max(value = 10, message = "El nivel no debe ser mayor de 10")
	private Integer nivelPadel;
	
}
