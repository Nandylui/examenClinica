package com.examen2.examen.controlador;

import com.examen2.examen.controlador.dto.FichaDTO;
import com.examen2.examen.modelo.Ficha;
import com.examen2.examen.modelo.Medico;
import com.examen2.examen.modelo.Paciente;
import com.examen2.examen.modelo.Usuario;
import com.examen2.examen.repositorio.FichaRepositorio;
import com.examen2.examen.repositorio.MedicoRepositorio;
import com.examen2.examen.repositorio.PacienteRepositorio;
import com.examen2.examen.servicio.BitacoraServicio;
import com.examen2.examen.servicio.FichaServicio;
import com.examen2.examen.servicio.UsuarioServicio;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/fichas")
public class FichaControlador {
    @Autowired
    private FichaRepositorio fichaRepositorio;

    @Autowired
    private PacienteRepositorio pacienteRepositorio;

    @Autowired
    private MedicoRepositorio medicoRepositorio;

    @Autowired
    private FichaServicio fichaServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private BitacoraServicio bitacoraServicio;

    @GetMapping({"", "/"})
    public String obtenerFichas(Model model, HttpServletRequest request) {
        List<Ficha> fichas = fichaRepositorio.findAll(); // Obtener todas las fichas
        model.addAttribute("fichas", fichas);

        // Registrar en bitácora que el admin creó un nueva ficha
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nombreUsuario = authentication.getName();
        Usuario usuario = usuarioServicio.encontrarPorNombreUsuario(nombreUsuario);
        String ip = request.getRemoteAddr();
        String dispositivo = request.getHeader("User-Agent");
        bitacoraServicio.registrarAccion(usuario, "Visito la vista ficha", dispositivo, ip);

        return "fichas/index"; // Vista para listar las fichas
    }

    @GetMapping("/crear")
    public String crearFicha(Model model, HttpServletRequest request) {
        FichaDTO fichaDTO = new FichaDTO();
        List<Paciente> pacientes = pacienteRepositorio.findAll(); // Obtener todos los pacientes
        List<Medico> medicos = medicoRepositorio.findAll(); // Obtener todos los médicos
        model.addAttribute("fichaDTO", fichaDTO);
        model.addAttribute("pacientes", pacientes);
        model.addAttribute("medicos", medicos);

        // el admin Accedio a la pagina de crear un nueva ficha
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nombreUsuario = authentication.getName();
        Usuario usuario = usuarioServicio.encontrarPorNombreUsuario(nombreUsuario);
        String ip = request.getRemoteAddr();
        String dispositivo = request.getHeader("User-Agent");
        bitacoraServicio.registrarAccion(usuario, "Accedio a la pagina de crear una ficha", dispositivo, ip);


        return "fichas/crear"; // Vista para crear ficha
    }

    @PostMapping("/crear")
    public String crearFicha(@Valid @ModelAttribute FichaDTO fichaDTO, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            List<Paciente> pacientes = pacienteRepositorio.findAll();
            List<Medico> medicos = medicoRepositorio.findAll();
            model.addAttribute("pacientes", pacientes);
            model.addAttribute("medicos", medicos);
            return "fichas/crear"; // Volver al formulario si hay errores
        }

        // Obtener paciente y medico por id
        Optional<Paciente> pacienteOpt = pacienteRepositorio.findById(fichaDTO.getPacienteId());
        Optional<Medico> medicoOpt = medicoRepositorio.findById(fichaDTO.getMedicoId());

        if (pacienteOpt.isEmpty() || medicoOpt.isEmpty()) {
            result.addError(new FieldError("fichaDTO", "pacienteId", "Paciente o Médico no encontrados"));
            List<Paciente> pacientes = pacienteRepositorio.findAll();
            List<Medico> medicos = medicoRepositorio.findAll();
            model.addAttribute("pacientes", pacientes);
            model.addAttribute("medicos", medicos);
            return "fichas/crear";
        }

        // Crear la ficha
        Ficha ficha = new Ficha();
        ficha.setFecha(fichaDTO.getFecha());
        ficha.setEstado(fichaDTO.getEstado());
        ficha.setPaciente(pacienteOpt.get());
        ficha.setMedico(medicoOpt.get());

        // Guardar la ficha
        fichaRepositorio.save(ficha);

        // el admin creó un nuevo ficha
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nombreUsuario = authentication.getName();
        Usuario usuario = usuarioServicio.encontrarPorNombreUsuario(nombreUsuario);
        String ip = request.getRemoteAddr();
        String dispositivo = request.getHeader("User-Agent");
        bitacoraServicio.registrarAccion(usuario, "Creo una ficha", dispositivo, ip);


        return "redirect:/fichas"; // Redirigir a la lista de fichas
    }

    @GetMapping("/editar/{id}")
    public String editarFicha(@PathVariable Long id, Model model) {
        Optional<Ficha> fichaOpt = fichaRepositorio.findById(id);
        if (fichaOpt.isEmpty()) {
            return "redirect:/fichas";
        }

        Ficha ficha = fichaOpt.get();
        FichaDTO fichaDTO = new FichaDTO();
        fichaDTO.setId(ficha.getId());
        fichaDTO.setFecha(ficha.getFecha());
        fichaDTO.setEstado(ficha.getEstado());
        fichaDTO.setPacienteId(ficha.getPaciente().getId());
        fichaDTO.setMedicoId(ficha.getMedico().getId());

        List<Paciente> pacientes = pacienteRepositorio.findAll();
        List<Medico> medicos = medicoRepositorio.findAll();

        model.addAttribute("fichaDTO", fichaDTO);
        model.addAttribute("pacientes", pacientes);
        model.addAttribute("medicos", medicos);

        return "fichas/editar"; // Vista para editar ficha
    }

    @PostMapping("/editar/{id}")
    public String editarFicha(@PathVariable Long id, @Valid @ModelAttribute FichaDTO fichaDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Paciente> pacientes = pacienteRepositorio.findAll();
            List<Medico> medicos = medicoRepositorio.findAll();
            model.addAttribute("pacientes", pacientes);
            model.addAttribute("medicos", medicos);
            return "fichas/editar"; // Si hay errores, volver al formulario
        }

        // Obtener paciente y medico por id
        Optional<Paciente> pacienteOpt = pacienteRepositorio.findById(fichaDTO.getPacienteId());
        Optional<Medico> medicoOpt = medicoRepositorio.findById(fichaDTO.getMedicoId());

        if (pacienteOpt.isEmpty() || medicoOpt.isEmpty()) {
            result.addError(new FieldError("fichaDTO", "pacienteId", "Paciente o Médico no encontrados"));
            List<Paciente> pacientes = pacienteRepositorio.findAll();
            List<Medico> medicos = medicoRepositorio.findAll();
            model.addAttribute("pacientes", pacientes);
            model.addAttribute("medicos", medicos);
            return "fichas/editar";
        }

        // Actualizar ficha
        Ficha ficha = fichaRepositorio.findById(id).get();
        ficha.setFecha(fichaDTO.getFecha());
        ficha.setEstado(fichaDTO.getEstado());
        ficha.setPaciente(pacienteOpt.get());
        ficha.setMedico(medicoOpt.get());

        // Guardar ficha actualizada
        fichaRepositorio.save(ficha);
        return "redirect:/fichas"; // Redirigir a la lista de fichas
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarFicha(@PathVariable Long id) {
        fichaRepositorio.deleteById(id);
        return "redirect:/fichas";
    }

}
