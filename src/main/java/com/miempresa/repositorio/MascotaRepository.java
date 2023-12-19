package com.miempresa.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miempresa.models.Mascota;

public interface MascotaRepository extends JpaRepository<Mascota, Long> {
    List<Mascota> findByNombre(String nombre);
}
