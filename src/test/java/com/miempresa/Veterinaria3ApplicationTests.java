package com.miempresa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.miempresa.models.UsuarioModel;
import com.miempresa.repositorio.IUsuario;

@SpringBootTest
class Veterinaria3ApplicationTests {

    @Autowired
    private IUsuario repo;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Test
    void crearUsuarios() {
        UsuarioModel user1 = new UsuarioModel();
        user1.setId(1);
        user1.setNombre("usuario1");
        user1.setClave(encoder.encode("12345"));
        user1.setRol("ROLE_USER");
        repo.save(user1);

        UsuarioModel user2 = new UsuarioModel();
        user2.setId(2);
        user2.setNombre("usuario2");
        user2.setClave(encoder.encode("12345"));
        user2.setRol("ROLE_ADMIN");
        repo.save(user2);
    }
}

