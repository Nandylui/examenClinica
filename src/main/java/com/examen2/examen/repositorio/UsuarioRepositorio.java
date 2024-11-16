package com.examen2.examen.repositorio;

import com.examen2.examen.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    public Usuario findByEmail(String email);
}
