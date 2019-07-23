package com.reser.padel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.reser.padel.domain.Pista;

public interface PistaRepository extends JpaRepository<Pista, Integer>{

    @Query("SELECT id FROM Pista WHERE nombre = :nombre") 
    List<Integer> findIdByNombre(@Param("nombre") String nombre);
}
