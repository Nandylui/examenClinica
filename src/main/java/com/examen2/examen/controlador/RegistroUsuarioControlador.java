package com.examen2.examen.controlador;

import com.examen2.examen.controlador.dto.UsuarioRegistroDTO;
import com.examen2.examen.modelo.Usuario;
import com.examen2.examen.servicio.UsuarioServicio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/registro")
public class RegistroUsuarioControlador {
    private UsuarioServicio usuarioServicio;

    public RegistroUsuarioControlador(UsuarioServicio usuarioServicio) {
        super();
        this.usuarioServicio = usuarioServicio;
    }

    @ModelAttribute("usuario")
    public UsuarioRegistroDTO retornarNuevoUsuarioRegistroDTO(){
        return new UsuarioRegistroDTO();
    }

    @GetMapping
    public String mostrarFormualarioDeRegistro() {
        return "registro";
    }

    @PostMapping
    public String registrarCuentaDeUsuario(@ModelAttribute("usuario") UsuarioRegistroDTO registroDTO) {
        usuarioServicio.guardar(registroDTO);
        return "redirect:/registro?exito";

    }

    // Nuevo método para mostrar la lista de usuarios
    @GetMapping("/usuarios")
    public String verUsuarios(Model model) {
        List<Usuario> usuarios = usuarioServicio.obtenerTodosLosUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "usuarios";
    }

}
