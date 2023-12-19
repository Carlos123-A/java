package com.miempresa.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miempresa.models.UsuarioModel;

public interface IUsuario extends JpaRepository<UsuarioModel, Integer> {
    UsuarioModel findByNombre(String nombre);
}
