package com.miempresa.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.miempresa.interfaceServicio.ClienteService;
import com.miempresa.models.Cliente;


import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
	
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/list")
    public String listarClientes(Model model) {
        List<Cliente> clientes = clienteService.obtenerTodosClientes();
        model.addAttribute("clientes", clientes);
        return "listaClientes";
    }

    @GetMapping("/form")
    public String mostrarFormularioCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "formularioCliente";
    }

    @PostMapping("/guardar")
    public String guardarCliente(@ModelAttribute Cliente cliente) {
        clienteService.guardarCliente(cliente);
        return "redirect:/clientes/list";
    }

    @GetMapping("/editar/{id}")
    public String editarCliente(@PathVariable Long id, Model model) {
        Optional<Cliente> optionalCliente = clienteService.obtenerClientePorId(id);

        if (optionalCliente.isPresent()) {
            Cliente cliente = optionalCliente.get();
            model.addAttribute("cliente", cliente);
            return "formularioCliente";
        } else {
          
            return "redirect:/clientes/list";
        }
    }


    @PostMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return "redirect:/clientes/list";
    }

}
