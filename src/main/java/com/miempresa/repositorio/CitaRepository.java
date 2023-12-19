package com.miempresa.repositorio;


import org.springframework.data.jpa.repository.JpaRepository;

import com.miempresa.models.Cita;


public interface CitaRepository extends JpaRepository<Cita, Long> {
    
}
