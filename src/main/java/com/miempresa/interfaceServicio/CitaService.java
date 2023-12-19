package com.miempresa.interfaceServicio;

import java.util.List;

import com.miempresa.models.Cita;

public interface CitaService {
    Cita guardarCita(Cita cita);
    List<Cita> obtenerTodasCitas();
    Cita obtenerCitaPorId(Long id);
    void eliminarCita(Long id);
    
}
