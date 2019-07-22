package com.everis.cestevez.padel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.everis.cestevez.padel.domain.Persona;

public interface PersonaRepository extends JpaRepository<Persona, String>{

}
