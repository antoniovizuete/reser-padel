package com.ejercicio.reserpadel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ejercicio.reserpadel.domain.Persona;

public interface PersonaRepository extends JpaRepository<Persona,String>{

}
