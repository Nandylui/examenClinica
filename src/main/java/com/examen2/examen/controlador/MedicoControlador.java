package com.examen2.examen.controlador;


import com.examen2.examen.controlador.dto.MedicoDTO;
import com.examen2.examen.modelo.Especialidad;
import com.examen2.examen.modelo.Medico;
import com.examen2.examen.modelo.Usuario;
import com.examen2.examen.repositorio.MedicoRepositorio;
import com.examen2.examen.servicio.BitacoraServicio;
import com.examen2.examen.servicio.EspecialidadServicio;
import com.examen2.examen.servicio.MedicoServicioImpl;
import com.examen2.examen.servicio.UsuarioServicio;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
@RequestMapping("/medicos")
public class MedicoControlador {
    @Autowired
    private MedicoRepositorio servicio;

    @Autowired
    private EspecialidadServicio especialidadServicio;

    @Autowired
    private MedicoServicioImpl medicoServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private BitacoraServicio bitacoraServicio;


//    @GetMapping({"","/"})
//    public String obtenerMedicos(Model model){
//        var medicos = servicio.findAll(Sort.by(Sort.Direction.ASC, "id"));
//        model.addAttribute("medicos", medicos);
//
//        return "medicos/index";
//    }

    //    esto me permite buscar y listar nombres
    @GetMapping({"", "/"})
    public String obtenerMedicos(@RequestParam(value = "nombre", required = false) String nombre, Model model, HttpServletRequest request){
        List<Medico> medicos;

        // Si se proporciona un nombre, busca médicos por nombre
        if (nombre != null && !nombre.isEmpty()) {
            medicos = servicio.findByNombreContainingIgnoreCase(nombre, Sort.by(Sort.Direction.ASC, "id"));
        } else {
            // Si no se proporciona un nombre, lista todos los médicos
            medicos = servicio.findAll(Sort.by(Sort.Direction.ASC, "id"));
        }

        model.addAttribute("medicos", medicos);
        model.addAttribute("nombre", nombre); // Para mostrar el valor de búsqueda en la barra de búsqueda

        // Registrar en bitácora que el admin creó un nuevo medico
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nombreUsuario = authentication.getName();
        Usuario usuario = usuarioServicio.encontrarPorNombreUsuario(nombreUsuario);
        String ip = request.getRemoteAddr();
        String dispositivo = request.getHeader("User-Agent");
        bitacoraServicio.registrarAccion(usuario, "Visito la vista medico", dispositivo, ip);


        return "medicos/index"; // El nombre de la vista para mostrar la lista de médicos
    }

    @GetMapping("/crear")
    public String crearMedico(Model model, HttpServletRequest request){
        MedicoDTO medicoDTO = new MedicoDTO();
        List<Especialidad> especialidades = especialidadServicio.obtenerTodasLasEspecialidades(); // Obtener todas las especialidades
        model.addAttribute("medicoDTO", medicoDTO);
        model.addAttribute("especialidades", especialidades);

        // el admin Accedio a la pagina de crear un nuevo medico
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nombreUsuario = authentication.getName();
        Usuario usuario = usuarioServicio.encontrarPorNombreUsuario(nombreUsuario);
        String ip = request.getRemoteAddr();
        String dispositivo = request.getHeader("User-Agent");
        bitacoraServicio.registrarAccion(usuario, "Accedio a la pagina de crear un medico", dispositivo, ip);


        return "medicos/crear";
    }



    @PostMapping("/crear")
    public String guardarMedico(@Valid @ModelAttribute("medicoDTO") MedicoDTO medicoDTO, BindingResult result, Model model, HttpServletRequest request) {

        // Validar errores
        if (result.hasErrors()) {
            List<Especialidad> especialidades = especialidadServicio.obtenerTodasLasEspecialidades();
            model.addAttribute("especialidades", especialidades);
            return "medicos/crear"; // Volver al formulario si hay errores
        }

        // Asignar los datos del DTO al objeto Medico
        Medico medico = new Medico();
        medico.setNombre(medicoDTO.getNombre());
        medico.setApellido(medicoDTO.getApellido());
        medico.setSexo(medicoDTO.getSexo());
        medico.setEmail(medicoDTO.getEmail());
        medico.setTelefono(medicoDTO.getTelefono());
        medico.setNromatricula(medicoDTO.getNromatricula());

        // Obtener y asignar la especialidad
        Especialidad especialidad = especialidadServicio.obtenerEspecialidadPorId(medicoDTO.getEspecialidadId()).orElse(null);
        if (especialidad == null) {
            result.addError(new FieldError("medicoDTO", "especialidadId", "Especialidad no encontrada"));
            List<Especialidad> especialidades = especialidadServicio.obtenerTodasLasEspecialidades();
            model.addAttribute("especialidades", especialidades);
            return "medicos/crear";
        }
        medico.setEspecialidad(especialidad);

        // Guardar el médico
        medicoServicio.guardarMedico(medico);

        // el admin creó un nuevo medico
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nombreUsuario = authentication.getName();
        Usuario usuario = usuarioServicio.encontrarPorNombreUsuario(nombreUsuario);
        String ip = request.getRemoteAddr();
        String dispositivo = request.getHeader("User-Agent");
        bitacoraServicio.registrarAccion(usuario, "Creo un medico", dispositivo, ip);

        return "redirect:/medicos"; // Redirigir al listado de médicos
    }


    @GetMapping("/editar/{id}")
    public String editarMedico(Model model, @PathVariable Long id, HttpServletRequest request){
        Medico medico = servicio.findById(id).orElse(null);
        if(medico == null){
            return "redirect:/medicos";
        }

        MedicoDTO medicoDTO = new MedicoDTO();
        medicoDTO.setNombre(medico.getNombre());
        medicoDTO.setApellido(medico.getApellido());
        medicoDTO.setSexo(medico.getSexo());
        medicoDTO.setEmail(medico.getEmail());
        medicoDTO.setTelefono(medico.getTelefono());
        medicoDTO.setNromatricula(medico.getNromatricula());
        medicoDTO.setEspecialidadId(medico.getEspecialidad().getId());  // Asignar la especialidad seleccionada

        List<Especialidad> especialidades = especialidadServicio.obtenerTodasLasEspecialidades();  // Obtener todas las especialidades
        model.addAttribute("medicoDTO", medicoDTO);
        model.addAttribute("medico", medico);
        model.addAttribute("especialidades", especialidades);  // Pasar las especialidades al modelo

        // el admin Accedio a la pagina para editar un medico
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nombreUsuario = authentication.getName();
        Usuario usuario = usuarioServicio.encontrarPorNombreUsuario(nombreUsuario);
        String ip = request.getRemoteAddr();
        String dispositivo = request.getHeader("User-Agent");
        bitacoraServicio.registrarAccion(usuario, "Accedio a la pagina para editar un medico con ID:" + id, dispositivo, ip);

        return "medicos/editar";
    }


    @PostMapping("/editar/{id}")
    public String editarMedico(@PathVariable Long id, @Valid @ModelAttribute MedicoDTO medicoDTO, BindingResult result, Model model, HttpServletRequest request){
        Medico medico = servicio.findById(id).orElse(null);
        if(medico == null){
            return "redirect:/medicos";
        }

        // Validar errores
        if (result.hasErrors()) {
            List<Especialidad> especialidades = especialidadServicio.obtenerTodasLasEspecialidades();
            model.addAttribute("especialidades", especialidades);
            return "medicos/editar";
        }

        // Asignar datos
        medico.setNombre(medicoDTO.getNombre());
        medico.setApellido(medicoDTO.getApellido());
        medico.setSexo(medicoDTO.getSexo());
        medico.setEmail(medicoDTO.getEmail());
        medico.setTelefono(medicoDTO.getTelefono());
        medico.setNromatricula(medicoDTO.getNromatricula());

        // Asignar la especialidad seleccionada
        Optional<Especialidad> especialidadOpt = especialidadServicio.obtenerEspecialidadPorId(medicoDTO.getEspecialidadId());
        if (especialidadOpt.isPresent()) {
            medico.setEspecialidad(especialidadOpt.get());
        } else {
            result.addError(new FieldError("medicoDTO", "especialidadId", "Especialidad no encontrada"));
            List<Especialidad> especialidades = especialidadServicio.obtenerTodasLasEspecialidades();
            model.addAttribute("especialidades", especialidades);
            return "medicos/editar";
        }

        // Guardar el médico actualizado
        servicio.save(medico);

        //el admin Edito el paciente un medico
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nombreUsuario = authentication.getName();
        Usuario usuario = usuarioServicio.encontrarPorNombreUsuario(nombreUsuario);
        String ip = request.getRemoteAddr();
        String dispositivo = request.getHeader("User-Agent");
        bitacoraServicio.registrarAccion(usuario, "Edito el medico con ID:" + id, dispositivo, ip);


        return "redirect:/medicos";
    }

    @GetMapping("/eliminar")
    public String eliminarMedico(@RequestParam Long id, HttpServletRequest request){
        Medico medico=servicio.findById(id).orElse(null);
        if(medico!= null){
            servicio.delete(medico);

            // el admin elimino un medico
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String nombreUsuario = authentication.getName();
            Usuario usuario = usuarioServicio.encontrarPorNombreUsuario(nombreUsuario);
            String ip = request.getRemoteAddr();
            String dispositivo = request.getHeader("User-Agent");
            bitacoraServicio.registrarAccion(usuario, nombreUsuario, dispositivo, ip);


        }
        return "redirect:/medicos";
    }


}
