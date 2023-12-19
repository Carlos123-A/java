package com.miempresa.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.miempresa.interfaceServicio.MascotaService;
import com.miempresa.models.Mascota;

import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/mascotas")
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    @GetMapping("/list")
    public String listarMascotas(Model model) {
        List<Mascota> mascotas = mascotaService.obtenerTodasMascotas();
        model.addAttribute("mascotas", mascotas);
        return "listaMascotas";
    }

    @GetMapping("/form")
    public String mostrarFormularioMascota(Model model) {
        model.addAttribute("mascota", new Mascota());
        return "formularioMascota";
    }

    @PostMapping("/guardar")
    public String guardarMascota(@ModelAttribute Mascota mascota) {
        mascotaService.guardarMascota(mascota);
        return "redirect:/mascotas/list";
    }

    @GetMapping("/editar/{id}")
    public String editarMascota(@PathVariable Long id, Model model) {
        Optional<Mascota> optionalMascota = mascotaService.obtenerMascotaPorId(id);
        
        if (optionalMascota.isPresent()) {
            Mascota mascota = optionalMascota.get();
            model.addAttribute("mascota", mascota);
            return "formularioMascota";
        } else {
            
            return "error";  
        }
    }

	    @PostMapping("/eliminar/{id}")
	    public String eliminarMascota(@PathVariable Long id) {
	        mascotaService.eliminarMascota(id);
	        return "redirect:/mascotas/list";
	    }


	}
