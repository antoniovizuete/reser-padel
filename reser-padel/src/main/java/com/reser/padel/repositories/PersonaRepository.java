package com.reser.padel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reser.padel.domain.Persona;

public interface PersonaRepository extends JpaRepository<Persona, String>{

}
