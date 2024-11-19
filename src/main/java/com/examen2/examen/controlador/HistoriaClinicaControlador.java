package com.examen2.examen.controlador;

import com.examen2.examen.controlador.dto.ConsultaDTO;
import com.examen2.examen.controlador.dto.HistoriaClinicaDTO;
import com.examen2.examen.modelo.Consulta;
import com.examen2.examen.modelo.HistoriaClinica;
import com.examen2.examen.modelo.Paciente;
import com.examen2.examen.modelo.Usuario;
import com.examen2.examen.repositorio.HistoriaClinicaRepositorio;
import com.examen2.examen.repositorio.PacienteRepositorio;
import com.examen2.examen.servicio.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/historiasclinicas")
public class HistoriaClinicaControlador {

    @Autowired
    private HistoriaClinicaRepositorio historiaClinicaRepositorio;

    @Autowired
    private PacienteRepositorio pacienteRepositorio;

    @Autowired
    private HistoriaClinicaServicio historiaClinicaServicio;

    @Autowired
    private MedicoServicio medicoServicio;

    @Autowired
    private ConsultaServicio consultaServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private BitacoraServicio bitacoraServicio;

    // Mostrar todas las historias clínicas
//    @GetMapping({"", "/"})
//    public String obtenerHistoriasClinicas(Model model) {
//        List<HistoriaClinica> historiasClinicas = historiaClinicaRepositorio.findAll();
//        model.addAttribute("historiasClinicas", historiasClinicas);
//        return "historiasclinicas/index"; // Vista para listar historias clínicas
//    }

    @GetMapping({"", "/"})
    public String listarHistorias(Model model, HttpServletRequest request) {
        List<HistoriaClinica> historias = historiaClinicaServicio.obtenerTodasLasHistoriasClinicas();
        model.addAttribute("historiasclinicas", historias);

        // Registrar en bitácora que el admin creó una nueva historia
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nombreUsuario = authentication.getName();
        Usuario usuario = usuarioServicio.encontrarPorNombreUsuario(nombreUsuario);
        String ip = request.getRemoteAddr();
        String dispositivo = request.getHeader("User-Agent");
        bitacoraServicio.registrarAccion(usuario, "Visito la vista historiaClinica", dispositivo, ip);

        return "historiasclinicas/index";
    }

    // Mostrar formulario para crear una nueva historia clínica
    @GetMapping("/crear")
    public String crearHistoriaClinica(Model model, HttpServletRequest request) {
        HistoriaClinicaDTO historiaClinicaDTO = new HistoriaClinicaDTO();
        List<Paciente> pacientes = pacienteRepositorio.findAll();
        model.addAttribute("historiaClinicaDTO", historiaClinicaDTO);
        model.addAttribute("pacientes", pacientes);

        // el admin Accedio a la pagina de crear un nuevo paciente
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nombreUsuario = authentication.getName();
        Usuario usuario = usuarioServicio.encontrarPorNombreUsuario(nombreUsuario);
        String ip = request.getRemoteAddr();
        String dispositivo = request.getHeader("User-Agent");
        bitacoraServicio.registrarAccion(usuario, "Accedio a la pagina de crear una historia", dispositivo, ip);


        return "historiasclinicas/crear"; // Vista para crear una historia clínica
    }

    // Guardar una nueva historia clínica
    @PostMapping("/crear")
    public String crearHistoriaClinica(@Valid @ModelAttribute HistoriaClinicaDTO historiaClinicaDTO, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            List<Paciente> pacientes = pacienteRepositorio.findAll();
            model.addAttribute("pacientes", pacientes);
            return "historiasclinicas/crear";
        }

        // Buscar el paciente por su ID
        Optional<Paciente> pacienteOpt = pacienteRepositorio.findById(historiaClinicaDTO.getPacienteId());
        if (pacienteOpt.isEmpty()) {
            result.addError(new FieldError("historiaClinicaDTO", "pacienteId", "Paciente no encontrado"));
            List<Paciente> pacientes = pacienteRepositorio.findAll();
            model.addAttribute("pacientes", pacientes);
            return "historiasclinicas/crear";
        }

        // Crear la historia clínica
        HistoriaClinica historiaClinica = new HistoriaClinica();
        historiaClinica.setFechaCreacion(historiaClinicaDTO.getFechaCreacion());
        historiaClinica.setObservacion(historiaClinicaDTO.getObservacion());
        historiaClinica.setPaciente(pacienteOpt.get());
        historiaClinicaRepositorio.save(historiaClinica);

        // el admin creó un nuevo paciente
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nombreUsuario = authentication.getName();
        Usuario usuario = usuarioServicio.encontrarPorNombreUsuario(nombreUsuario);
        String ip = request.getRemoteAddr();
        String dispositivo = request.getHeader("User-Agent");
        bitacoraServicio.registrarAccion(usuario, "Creo una historiaClinica", dispositivo, ip);


        return "redirect:/historiasclinicas";
    }

    // Mostrar formulario para editar una historia clínica existente
    @GetMapping("/editar/{id}")
    public String editarHistoriaClinica(@PathVariable Long id, Model model) {
        Optional<HistoriaClinica> historiaClinicaOpt = historiaClinicaRepositorio.findById(id);
        if (historiaClinicaOpt.isEmpty()) {
            return "redirect:/historiasclinicas";
        }

        HistoriaClinica historiaClinica = historiaClinicaOpt.get();
        HistoriaClinicaDTO historiaClinicaDTO = new HistoriaClinicaDTO();
        historiaClinicaDTO.setId(historiaClinica.getId());
        historiaClinicaDTO.setFechaCreacion(historiaClinica.getFechaCreacion());
        historiaClinicaDTO.setPacienteId(historiaClinica.getPaciente().getId());

        List<Paciente> pacientes = pacienteRepositorio.findAll();
        model.addAttribute("historiaClinicaDTO", historiaClinicaDTO);
        model.addAttribute("pacientes", pacientes);


        return "historiasclinicas/editar"; // Vista para editar historia clínica
    }


//    @GetMapping("/historiasclinicas/{id}")
//    public String verHistoriaClinica(@PathVariable("id") Long id, Model model) {
//        // Aquí cargarías la historia clínica
//        model.addAttribute("historiaClinica", historiaClinicaServicio.obtenerHistoriaClinicaPorId(id));
//        return "historiasclinicas/ver";
//    }


    @GetMapping("/consultas/{id}")
    public String editarConsulta(@PathVariable("id") Long historiaId, Model model) {
        // Lógica para cargar la consulta asociada a esa historia clínica
        model.addAttribute("consultaDTO", new ConsultaDTO());  // ConsultaDTO es un ejemplo
        model.addAttribute("historiasClinicas", historiaClinicaServicio.obtenerTodasLasHistoriasClinicas());
        model.addAttribute("medicos", medicoServicio.obtenerTodosMedicos());
        return "consultas/editarconsulta";  // Redirige a la vista editarconsulta.html
    }


    // Actualizar una historia clínica existente
    @PostMapping("/editar/{id}")
    public String editarHistoriaClinica(@PathVariable Long id, @Valid @ModelAttribute HistoriaClinicaDTO historiaClinicaDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Paciente> pacientes = pacienteRepositorio.findAll();
            model.addAttribute("pacientes", pacientes);
            return "historiasclinicas/editar";
        }

        // Buscar el paciente por su ID
        Optional<Paciente> pacienteOpt = pacienteRepositorio.findById(historiaClinicaDTO.getPacienteId());
        if (pacienteOpt.isEmpty()) {
            result.addError(new FieldError("historiaClinicaDTO", "pacienteId", "Paciente no encontrado"));
            List<Paciente> pacientes = pacienteRepositorio.findAll();
            model.addAttribute("pacientes", pacientes);
            return "historiasclinicas/editar";
        }

        // Actualizar la historia clínica
        HistoriaClinica historiaClinica = historiaClinicaRepositorio.findById(id).get();
        historiaClinica.setFechaCreacion(historiaClinicaDTO.getFechaCreacion());
        historiaClinica.setPaciente(pacienteOpt.get());
        historiaClinicaRepositorio.save(historiaClinica);

        return "redirect:/historiasclinicas";
    }

    // Eliminar una historia clínica
    @GetMapping("/eliminar/{id}")
    public String eliminarHistoriaClinica(@PathVariable Long id) {
        historiaClinicaRepositorio.deleteById(id);
        return "redirect:/historiasclinicas";
    }

    @GetMapping("/historiasclinicas/{id}")
    public String verHistoriaClinica(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
        // Obtener la historia clínica por su ID
        Optional<HistoriaClinica> historiaClinicaOpt = historiaClinicaServicio.obtenerHistoriaClinicaPorId(id);

        // Verificar si se encontró la historia clínica
        if (historiaClinicaOpt.isEmpty()) {
            // Redirigir a la lista si no se encuentra
            return "redirect:/historiasclinicas";
        }

        // Obtener la historia clínica de dentro del Optional
        HistoriaClinica historiaClinica = historiaClinicaOpt.get();

        // Obtener las consultas asociadas a la historia clínica
        List<Consulta> consultas = historiaClinica.getConsultas(); // Asumiendo que HistoriaClinica tiene una lista de consultas

        // Pasar la historia clínica y las consultas al modelo
        model.addAttribute("historiaClinica", historiaClinica);
        model.addAttribute("consultas", consultas);

        // el admin Accedio a la pagina para editar un paciente
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nombreUsuario = authentication.getName();
        Usuario usuario = usuarioServicio.encontrarPorNombreUsuario(nombreUsuario);
        String ip = request.getRemoteAddr();
        String dispositivo = request.getHeader("User-Agent");
        bitacoraServicio.registrarAccion(usuario, "Accedio a la pagina para ver una historiaClinica con ID:" + id, dispositivo, ip);


        return "historiasclinicas/ver"; // Vista que muestra la historia clínica y las consultas
    }


}
