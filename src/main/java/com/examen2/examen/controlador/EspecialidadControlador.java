package com.examen2.examen.controlador;

import com.examen2.examen.controlador.dto.EspecialidadDTO;
import com.examen2.examen.modelo.Especialidad;
import com.examen2.examen.modelo.Usuario;
import com.examen2.examen.repositorio.EspecialidadRepositorio;
import com.examen2.examen.servicio.BitacoraServicio;
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

@Controller
@RequestMapping("/especialidades")
public class EspecialidadControlador {

    @Autowired
    private EspecialidadRepositorio especialidadRepositorio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private BitacoraServicio bitacoraServicio;


    // Método para obtener todas las especialidades o buscar por nombre
    @GetMapping({"", "/"})
    public String obtenerEspecialidades(@RequestParam(value = "nombre", required = false) String nombre, Model model, HttpServletRequest request) {
        List<Especialidad> especialidades;

        // Si se proporciona un nombre, buscamos especialidades por nombre
        if (nombre != null && !nombre.isEmpty()) {
            especialidades = especialidadRepositorio.findByNombreContainingIgnoreCase(nombre, Sort.by(Sort.Direction.ASC, "id"));
        } else {
            // Si no se proporciona un nombre, se lista todas las especialidades
            especialidades = especialidadRepositorio.findAll(Sort.by(Sort.Direction.ASC, "id"));
        }

        model.addAttribute("especialidades", especialidades);
        model.addAttribute("nombre", nombre);  // Para mostrar el valor de búsqueda en la barra de búsqueda

        // Registrar en bitácora que el admin creó un nueva especialidad
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nombreUsuario = authentication.getName();
        Usuario usuario = usuarioServicio.encontrarPorNombreUsuario(nombreUsuario);
        String ip = request.getRemoteAddr();
        String dispositivo = request.getHeader("User-Agent");
        bitacoraServicio.registrarAccion(usuario, "Visito la vista especialidad", dispositivo, ip);


        return "especialidades/index";  // El nombre de la vista para mostrar la lista de especialidades
    }

    // Vista para crear una nueva especialidad
    @GetMapping("/crear")
    public String crearEspecialidad(Model model, HttpServletRequest request) {
        EspecialidadDTO especialidadDTO = new EspecialidadDTO();
        model.addAttribute("especialidadDTO", especialidadDTO);

        // el admin Accedio a la pagina de crear un nueva especialidad
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nombreUsuario = authentication.getName();
        Usuario usuario = usuarioServicio.encontrarPorNombreUsuario(nombreUsuario);
        String ip = request.getRemoteAddr();
        String dispositivo = request.getHeader("User-Agent");
        bitacoraServicio.registrarAccion(usuario, "Accedio a la pagina de crear nueva especialidad", dispositivo, ip);

        return "especialidades/crear";
    }

    // Crear una nueva especialidad
    @PostMapping("/crear")
    public String crearEspecialidad(@Valid @ModelAttribute EspecialidadDTO especialidadDTO, BindingResult result, HttpServletRequest request) {
        if (especialidadRepositorio.findByNombre(especialidadDTO.getNombre()) != null) {
            result.addError(new FieldError("especialidadDTO", "nombre", especialidadDTO.getNombre(), false, null, null, "Nombre de especialidad ya usado"));
        }
        if (result.hasErrors()) {
            return "especialidades/crear";
        }

        Especialidad especialidad = new Especialidad();
        especialidad.setNombre(especialidadDTO.getNombre());
        especialidad.setDescripcion(especialidadDTO.getDescripcion());
        especialidadRepositorio.save(especialidad);

        // el admin creó un nuevo especialidad
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nombreUsuario = authentication.getName();
        Usuario usuario = usuarioServicio.encontrarPorNombreUsuario(nombreUsuario);
        String ip = request.getRemoteAddr();
        String dispositivo = request.getHeader("User-Agent");
        bitacoraServicio.registrarAccion(usuario, "Creo una especialidad", dispositivo, ip);

        return "redirect:/especialidades";
    }

    // Vista para editar una especialidad
    @GetMapping("/editar/{id}")
    public String editarEspecialidad(Model model, @PathVariable Long id, HttpServletRequest request) {
        Especialidad especialidad = especialidadRepositorio.findById(id).orElse(null);
        if (especialidad == null) {
            return "redirect:/especialidades";
        }

        EspecialidadDTO especialidadDTO = new EspecialidadDTO();
        especialidadDTO.setNombre(especialidad.getNombre());
        especialidadDTO.setDescripcion(especialidad.getDescripcion());

        model.addAttribute("especialidadDTO", especialidadDTO);
        model.addAttribute("especialidad", especialidad);

        // el admin Accedio a la pagina para editar una especialidad
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nombreUsuario = authentication.getName();
        Usuario usuario = usuarioServicio.encontrarPorNombreUsuario(nombreUsuario);
        String ip = request.getRemoteAddr();
        String dispositivo = request.getHeader("User-Agent");
        bitacoraServicio.registrarAccion(usuario, "Accedio a la pagina para editar una especialidad con ID:" + id, dispositivo, ip);


        return "especialidades/editar";
    }

    // Editar una especialidad
    @PostMapping("/editar/{id}")
    public String editarEspecialidad(@PathVariable Long id, @Valid @ModelAttribute EspecialidadDTO especialidadDTO, BindingResult result, Model model, HttpServletRequest request) {
        Especialidad especialidad = especialidadRepositorio.findById(id).orElse(null);
        if (especialidad == null) {
            return "redirect:/especialidades";
        }

        if (result.hasErrors()) {
            model.addAttribute("especialidad", especialidad); // Para mantener el ID en la plantilla
            return "especialidades/editar";
        }

        especialidad.setNombre(especialidadDTO.getNombre());
        especialidad.setDescripcion(especialidadDTO.getDescripcion());

        try {
            especialidadRepositorio.save(especialidad);
        } catch (Exception e) {
            result.addError(new FieldError("especialidadDTO", "nombre", especialidadDTO.getNombre(), false, null, null, "Nombre ya en uso"));
            model.addAttribute("especialidad", especialidad);
            return "especialidades/editar";
        }

        //el admin Edito la especialidad
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nombreUsuario = authentication.getName();
        Usuario usuario = usuarioServicio.encontrarPorNombreUsuario(nombreUsuario);
        String ip = request.getRemoteAddr();
        String dispositivo = request.getHeader("User-Agent");
        bitacoraServicio.registrarAccion(usuario, "Edito la especialidad con ID:" + id, dispositivo, ip);


        return "redirect:/especialidades";
    }

    // Eliminar una especialidad
    @GetMapping("/eliminar")
    public String eliminarEspecialidad(@RequestParam Long id, HttpServletRequest request) {
        Especialidad especialidad = especialidadRepositorio.findById(id).orElse(null);
        if (especialidad != null) {
            especialidadRepositorio.delete(especialidad);

            // el admin elimino una especialidad
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String nombreUsuario = authentication.getName();
            Usuario usuario = usuarioServicio.encontrarPorNombreUsuario(nombreUsuario);
            String ip = request.getRemoteAddr();
            String dispositivo = request.getHeader("User-Agent");
            bitacoraServicio.registrarAccion(usuario, nombreUsuario, dispositivo, ip);
        }
        return "redirect:/especialidades";
    }
}
