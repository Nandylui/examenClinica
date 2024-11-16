package com.examen2.examen.controlador;

import com.examen2.examen.controlador.dto.PacienteDTO;
import com.examen2.examen.modelo.Paciente;
import com.examen2.examen.repositorio.PacienteRepositorio;
import com.examen2.examen.servicio.PacienteServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/pacientes")
public class PacienteControlador {

    @Autowired
    private PacienteRepositorio servicio;

    @Autowired
    private PacienteServicioImpl pacienteServicio;

    @GetMapping({"", "/"})
    public String obtenerPacientes(@RequestParam(value = "nombre", required = false) String nombre, Model model) {
        List<Paciente> pacientes;

        if (nombre != null && !nombre.isEmpty()) {
            pacientes = servicio.findByNombreContainingIgnoreCase(nombre, Sort.by(Sort.Direction.ASC, "id"));
        } else {
            pacientes = servicio.findAll(Sort.by(Sort.Direction.ASC, "id"));
        }

        model.addAttribute("pacientes", pacientes);
        model.addAttribute("nombre", nombre);

        return "pacientes/index"; // Vista para la lista de pacientes
    }

    @GetMapping("/crear")
    public String crearPaciente(Model model) {
        PacienteDTO pacienteDTO = new PacienteDTO();
        model.addAttribute("pacienteDTO", pacienteDTO);
        return "pacientes/crear";
    }

    @PostMapping("/crear")
    public String guardarPaciente(@Valid @ModelAttribute("pacienteDTO") PacienteDTO pacienteDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "pacientes/crear"; // Retornar al formulario si hay errores
        }

        Paciente paciente = new Paciente();
        paciente.setNombre(pacienteDTO.getNombre());
        paciente.setApellido(pacienteDTO.getApellido());
        paciente.setSexo(pacienteDTO.getSexo());
        paciente.setEmail(pacienteDTO.getEmail());
        paciente.setTelefono(pacienteDTO.getTelefono());
        paciente.setFechaNacimiento(pacienteDTO.getFechaNacimiento());
        paciente.setNroDocumento(pacienteDTO.getNroDocumento());
        paciente.setDireccion(pacienteDTO.getDireccion());
        paciente.setFechaRegistro(pacienteDTO.getFechaRegistro());


        pacienteServicio.guardarPaciente(paciente);

        return "redirect:/pacientes";
    }

    @GetMapping("/editar/{id}")
    public String editarPaciente(Model model, @PathVariable Long id) {
        Paciente paciente = servicio.findById(id).orElse(null);
        if (paciente == null) {
            return "redirect:/pacientes";
        }

        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setNombre(paciente.getNombre());
        pacienteDTO.setApellido(paciente.getApellido());
        pacienteDTO.setSexo(paciente.getSexo());
        pacienteDTO.setEmail(paciente.getEmail());
        pacienteDTO.setTelefono(paciente.getTelefono());
        pacienteDTO.setFechaNacimiento(paciente.getFechaNacimiento());
        pacienteDTO.setNroDocumento(paciente.getNroDocumento());  // Agregar nroDocumento
        pacienteDTO.setDireccion(paciente.getDireccion());        // Agregar direccion
        pacienteDTO.setFechaRegistro(paciente.getFechaRegistro());  // Agregar fechaRegistro

        model.addAttribute("pacienteDTO", pacienteDTO);
        model.addAttribute("paciente", paciente);
        return "pacientes/editar";
    }

    @PostMapping("/editar/{id}")
    public String editarPaciente(@PathVariable Long id, @Valid @ModelAttribute PacienteDTO pacienteDTO, BindingResult result, Model model) {
        Paciente paciente = servicio.findById(id).orElse(null);
        if (paciente == null) {
            return "redirect:/pacientes";
        }

        if (result.hasErrors()) {
            return "pacientes/editar";
        }

        // Actualizar solo los campos que han cambiado, no sobrescribir la fecha de registro
        paciente.setNombre(pacienteDTO.getNombre());
        paciente.setApellido(pacienteDTO.getApellido());
        paciente.setSexo(pacienteDTO.getSexo());
        paciente.setEmail(pacienteDTO.getEmail());
        paciente.setTelefono(pacienteDTO.getTelefono());
        paciente.setFechaNacimiento(pacienteDTO.getFechaNacimiento());
        paciente.setNroDocumento(pacienteDTO.getNroDocumento());
        paciente.setDireccion(pacienteDTO.getDireccion());

        // Mantener la fecha de registro original
        paciente.setFechaRegistro(paciente.getFechaRegistro());  // Mantener la fecha de registro sin cambios

        pacienteServicio.guardarPaciente(paciente);

        return "redirect:/pacientes";
    }

    @GetMapping("/eliminar")
    public String eliminarPaciente(@RequestParam Long id) {
        Paciente paciente = servicio.findById(id).orElse(null);
        if (paciente != null) {
            servicio.delete(paciente);
        }
        return "redirect:/pacientes";
    }

}
