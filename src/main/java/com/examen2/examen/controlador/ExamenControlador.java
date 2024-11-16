package com.examen2.examen.controlador;

import com.examen2.examen.modelo.Examen;
import com.examen2.examen.servicio.ExamenServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/examenes")
public class ExamenControlador {
    private final ExamenServicio examenServicio;

    @Autowired
    public ExamenControlador(ExamenServicio examenServicio) {
        this.examenServicio = examenServicio;
    }

    @PostMapping("/crear")
    public ResponseEntity<Examen> crearExamen(@RequestBody Examen examen) {
        return new ResponseEntity<>(examenServicio.guardarExamen(examen), HttpStatus.CREATED);
    }

    @GetMapping("/detalle/{id}")
    public ResponseEntity<Examen> obtenerExamen(@PathVariable Long id) {
        return ResponseEntity.ok(examenServicio.obtenerExamenPorId(id));
    }

    @GetMapping("/consulta/{consultaId}")
    public List<Examen> obtenerExamenesPorConsulta(@PathVariable Long consultaId) {
        return examenServicio.obtenerExamenesPorConsulta(consultaId);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Examen> actualizarExamen(@PathVariable Long id, @RequestBody Examen examen) {
        return ResponseEntity.ok(examenServicio.actualizarExamen(id, examen));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarExamen(@PathVariable Long id) {
        examenServicio.eliminarExamen(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
