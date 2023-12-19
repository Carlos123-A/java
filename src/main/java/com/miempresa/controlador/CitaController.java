package com.miempresa.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.miempresa.interfaceServicio.CitaService;
import com.miempresa.interfaceServicio.ClienteService;
import com.miempresa.interfaceServicio.MascotaService;
import com.miempresa.models.Cita;
import com.miempresa.models.Cliente;



@Controller
@RequestMapping("/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private MascotaService mascotaService;

    @GetMapping("/list")
    public String listarCitas(Model model) {
        List<Cita> citas = citaService.obtenerTodasCitas();
        model.addAttribute("citas", citas);
        return "listaCitas";
    }

    @GetMapping("/form")
    public String mostrarFormularioCita(Model model) {
        model.addAttribute("cita", new Cita());
        model.addAttribute("clientes", clienteService.obtenerTodosClientes());
        model.addAttribute("mascotas", mascotaService.obtenerTodasMascotas());
        return "formularioCita";
    }

    @PostMapping("/guardar")
    public String guardarCita(@ModelAttribute Cita cita) {
        
        Cliente cliente = clienteService.buscarClientePorDni(cita.getCliente().getDni());

        
        if (cliente == null) {
            clienteService.guardarCliente(cita.getCliente());
            cliente = clienteService.buscarClientePorDni(cita.getCliente().getDni());
        }

      
        cita.setCliente(cliente);

       
        citaService.guardarCita(cita);

        return "redirect:/citas/list";
    }



    @GetMapping("/editar/{id}")
    public String editarCita(@PathVariable Long id, Model model) {
        Cita cita = citaService.obtenerCitaPorId(id);
        model.addAttribute("cita", cita);
        model.addAttribute("clientes", clienteService.obtenerTodosClientes());
        model.addAttribute("mascotas", mascotaService.obtenerTodasMascotas());
        return "formularioCita";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarCita(@PathVariable Long id) {
        citaService.eliminarCita(id);
        return "redirect:/citas/list";
    }
}
