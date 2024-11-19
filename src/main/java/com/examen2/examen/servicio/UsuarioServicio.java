package com.examen2.examen.servicio;

import com.examen2.examen.controlador.dto.UsuarioRegistroDTO;
import com.examen2.examen.modelo.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UsuarioServicio extends UserDetailsService {
    public Usuario guardar(UsuarioRegistroDTO registroDTO);
    public List<Usuario> listarUsuarios();
    public List<Usuario> obtenerTodosLosUsuarios();
    public Usuario encontrarPorNombreUsuario(String nombreUsuario);

}
