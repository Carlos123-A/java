package com.miempresa.interfaceServicio;

import com.miempresa.models.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    Cliente guardarCliente(Cliente cliente);
    List<Cliente> obtenerTodosClientes();
    Optional<Cliente> obtenerClientePorId(Long id);
    void eliminarCliente(Long id);
    Cliente buscarClientePorDni(String dni);
}