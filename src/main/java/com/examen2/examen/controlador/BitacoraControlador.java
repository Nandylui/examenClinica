package com.examen2.examen.controlador;

import com.examen2.examen.modelo.Usuario;
import com.examen2.examen.repositorio.BitacoraRepositorio;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bitacoras")
public class BitacoraControlador {
    @Autowired
    public BitacoraRepositorio servicio;

    @GetMapping({"","/listarbitacora"})
    public String listarBitacora(Model modelo){
        var bitacoras = servicio.findAll();
        modelo.addAttribute("bitacora", bitacoras);
        return "bitacoras/index";
    }

//    @PostMapping("/registrar")
//    public String registrarAccion(HttpServletRequest request) {
//        // Aquí puedes obtener el usuario, acción, y detalle del dispositivo
//        Usuario usuario = // lógica para obtener usuario actual
//                String accion = "Accedió a la bitácora";
//        String dispositivo = request.getHeader("User-Agent"); // Detalle del dispositivo
//
//        BitacoraServicio.registrarAccion(usuario, accion, dispositivo, request);
//        return "redirect:/bitacoras/listarbitacora";
//    }



}
