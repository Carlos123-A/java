package com.miempresa.interfaceServicio;

import java.util.List;
import java.util.Optional;

import com.miempresa.models.Mascota;

public interface MascotaService {
    Mascota guardarMascota(Mascota mascota);
    List<Mascota> obtenerTodasMascotas();
    Optional<Mascota> obtenerMascotaPorId(Long id);
    void eliminarMascota(Long id);
    
}
