package com.miempresa.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miempresa.interfaceServicio.CitaService;
import com.miempresa.models.Cita;
import com.miempresa.repositorio.CitaRepository;

@Service
public class CitaServiceImpl implements CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Override
    public Cita guardarCita(Cita cita) {
        return citaRepository.save(cita);
    }

    @Override
    public List<Cita> obtenerTodasCitas() {
        return citaRepository.findAll();
    }

    @Override
    public Cita obtenerCitaPorId(Long id) {
        return citaRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarCita(Long id) {
        citaRepository.deleteById(id);
    }
}
