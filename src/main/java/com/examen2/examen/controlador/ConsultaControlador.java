package com.examen2.examen.controlador;

import com.examen2.examen.controlador.dto.ConsultaDTO;
import com.examen2.examen.modelo.Consulta;
import com.examen2.examen.modelo.HistoriaClinica;
import com.examen2.examen.modelo.Medico;
import com.examen2.examen.repositorio.HistoriaClinicaRepositorio;
import com.examen2.examen.repositorio.MedicoRepositorio;
import com.examen2.examen.servicio.ConsultaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/consultas")
public class ConsultaControlador {

    @Autowired
    private ConsultaServicio consultaServicio;

    @Autowired
    private MedicoRepositorio medicoRepositorio;

    @Autowired
    private HistoriaClinicaRepositorio historiaClinicaRepositorio;

    @GetMapping("/crear")
    public String crearConsulta(Model model) {
        ConsultaDTO consultaDTO = new ConsultaDTO();
        List<Medico> medicos = medicoRepositorio.findAll();
        List<HistoriaClinica> historiasClinicas = historiaClinicaRepositorio.findAll();

        model.addAttribute("consultaDTO", consultaDTO);
        model.addAttribute("medicos", medicos);
        model.addAttribute("historiasClinicas", historiasClinicas);
        return "consultas/crear"; // Vista para crear consulta
    }

    @PostMapping("/crear")
    public String crearConsulta(@Valid @ModelAttribute ConsultaDTO consultaDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Medico> medicos = medicoRepositorio.findAll();
            List<HistoriaClinica> historiasClinicas = historiaClinicaRepositorio.findAll();
            model.addAttribute("medicos", medicos);
            model.addAttribute("historiasClinicas", historiasClinicas);
            return "consultas/crear"; // Volver al formulario si hay errores
        }

//        // Verifica que el historiaClinicaId no esté vacío
//        System.out.println("HistoriaClinicaId: " + consultaDTO.getHistoriaClinicaId());


        // Mapear el DTO a la entidad Consulta
        Consulta consulta = new Consulta();
        consulta.setFecha(consultaDTO.getFecha());
        consulta.setDiagnostico(consultaDTO.getDiagnostico());
        consulta.setTratamiento(consultaDTO.getTratamiento());
        consulta.setMedico(medicoRepositorio.findById(consultaDTO.getMedicoId()).orElse(null));
        consulta.setHistoriaClinica(historiaClinicaRepositorio.findById(consultaDTO.getHistoriaClinicaId()).orElse(null));

        consultaServicio.crearConsulta(consulta);
        return "redirect:/consultas"; // Redirigir a la lista de consultas
    }

    @GetMapping("/editar/{id}")
    public String editarConsulta(@PathVariable Long id, Model model) {
        Consulta consulta = consultaServicio.obtenerConsultaPorId(id);
        if (consulta == null) {
            return "redirect:/consultas"; // Redirigir si no se encuentra la consulta
        }

        // Mapear la entidad a un DTO
        ConsultaDTO consultaDTO = new ConsultaDTO();
        consultaDTO.setId(consulta.getId());
        consultaDTO.setFecha(consulta.getFecha());
        consultaDTO.setDiagnostico(consulta.getDiagnostico());
        consultaDTO.setTratamiento(consulta.getTratamiento());
        consultaDTO.setMedicoId(consulta.getMedico().getId());
        consultaDTO.setHistoriaClinicaId(consulta.getHistoriaClinica().getId());

        List<Medico> medicos = medicoRepositorio.findAll();
        List<HistoriaClinica> historiasClinicas = historiaClinicaRepositorio.findAll();

        model.addAttribute("consultaDTO", consultaDTO);
        model.addAttribute("medicos", medicos);
        model.addAttribute("historiasClinicas", historiasClinicas);
        return "consultas/crear"; // Vista para editar consulta
    }

    @PostMapping("/editar/{id}")
    public String editarConsulta(@PathVariable Long id, @Valid @ModelAttribute ConsultaDTO consultaDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Medico> medicos = medicoRepositorio.findAll();
            List<HistoriaClinica> historiasClinicas = historiaClinicaRepositorio.findAll();
            model.addAttribute("medicos", medicos);
            model.addAttribute("historiasClinicas", historiasClinicas);
            return "consultas/crear"; // Volver al formulario si hay errores
        }

        // Mapear el DTO a la entidad Consulta
        Consulta consulta = new Consulta();
        consulta.setId(id);
        consulta.setFecha(consultaDTO.getFecha());
        consulta.setDiagnostico(consultaDTO.getDiagnostico());
        consulta.setTratamiento(consultaDTO.getTratamiento());
        consulta.setMedico(medicoRepositorio.findById(consultaDTO.getMedicoId()).orElse(null));
        consulta.setHistoriaClinica(historiaClinicaRepositorio.findById(consultaDTO.getHistoriaClinicaId()).orElse(null));

        consultaServicio.actualizarConsulta(id, consulta);
        return "redirect:/consultas"; // Redirigir a la lista de consultas
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarConsulta(@PathVariable Long id) {
        consultaServicio.eliminarConsulta(id);
        return "redirect:/consultas";
    }

    @GetMapping("/consultas/{id}")
    public String verConsulta(@PathVariable("id") Long id, Model model) {
        Consulta consulta = consultaServicio.obtenerConsultaPorId(id);
        model.addAttribute("consulta", consulta);
        return "verConsulta";  // nombre de la vista
    }

    @GetMapping("/historia/{id}")
    public String mostrarConsultasPorHistoria(@PathVariable Long id, Model model) {
        List<Consulta> consultas = consultaServicio.obtenerConsultasPorHistoriaClinica(id);
        model.addAttribute("consultas", consultas);
        return "consultas/verConsultas"; // Nombre de la vista donde mostrarás las consultas
    }

}