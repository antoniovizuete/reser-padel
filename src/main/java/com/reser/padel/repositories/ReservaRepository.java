package com.reser.padel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reser.padel.domain.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {

}
