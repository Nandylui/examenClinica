package com.examen2.examen.controlador;

import com.examen2.examen.modelo.Consulta;
import com.examen2.examen.servicio.ConsultaServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/consultas")
public class ConsultaControlador {

    private ConsultaServicio consultaServicio;

//    @Autowired
//    public ConsultaController(ConsultaService consultaService) {
//        this.consultaService = consultaService;
//    }

    @PostMapping("/crear")
    public ResponseEntity<Consulta> crearConsulta(@RequestBody Consulta consulta) {
        return ResponseEntity.ok(consultaServicio.crearConsulta(consulta));
    }

    @GetMapping("/detalle/{id}")
    public ResponseEntity<Consulta> obtenerConsultaPorId(@PathVariable Long id) {
        Consulta consulta = consultaServicio.obtenerConsultaPorId(id);
        return consulta != null ? ResponseEntity.ok(consulta) : ResponseEntity.notFound().build();
    }

    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<List<Consulta>> obtenerConsultasPorMedico(@PathVariable Long medicoId) {
        return ResponseEntity.ok(consultaServicio.obtenerConsultasPorMedico(medicoId));
    }

    @GetMapping("/historias-clinicas/{historiaClinicaId}")
    public ResponseEntity<List<Consulta>> obtenerConsultasPorHistoriaClinica(@PathVariable Long historiaClinicaId) {
        return ResponseEntity.ok(consultaServicio.obtenerConsultasPorHistoriaClinica(historiaClinicaId));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Consulta> actualizarConsulta(@PathVariable Long id, @RequestBody Consulta consulta) {
        Consulta consultaActualizada = consultaServicio.actualizarConsulta(id, consulta);
        return consultaActualizada != null ? ResponseEntity.ok(consultaActualizada) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarConsulta(@PathVariable Long id) {
        consultaServicio.eliminarConsulta(id);
        return ResponseEntity.noContent().build();
    }

}
