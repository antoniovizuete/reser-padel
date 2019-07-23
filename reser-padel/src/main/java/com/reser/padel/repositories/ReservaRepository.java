package com.reser.padel.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.reser.padel.domain.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Integer>{

    @Query("SELECT id FROM Reserva WHERE fecha = :fecha AND pista_id = :pista") 
    List<Integer> findIdByFechaPista(@Param("fecha") LocalDateTime fecha, @Param("pista") Integer pista);
    
}
