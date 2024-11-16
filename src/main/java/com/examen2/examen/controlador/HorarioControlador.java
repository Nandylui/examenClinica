package com.examen2.examen.controlador;

import com.examen2.examen.modelo.Horario;
import com.examen2.examen.servicio.HorarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/horarios")
public class HorarioControlador {

    private HorarioServicio horarioServicio;

    @Autowired
    public HorarioControlador(HorarioServicio horarioServicio) {
        this.horarioServicio = horarioServicio;
    }

    @PostMapping("/crear")
    public ResponseEntity<Horario> crearHorario(@RequestBody Horario horario) {
        return new ResponseEntity<>(horarioServicio.guardarHorario(horario), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Horario> obtenerHorario(@PathVariable Long id) {
        return horarioServicio.obtenerHorarioPorId(id)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<Horario> obtenerTodosLosHorarios() {
        return horarioServicio.obtenerTodosLosHorarios();
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Horario> actualizarHorario(@PathVariable Long id, @RequestBody Horario horario) {
        return new ResponseEntity<>(horarioServicio.actualizarHorario(id, horario), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarHorario(@PathVariable Long id) {
        horarioServicio.eliminarHorario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
