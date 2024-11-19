package com.examen2.examen.servicio;

import com.examen2.examen.modelo.Medico;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface MedicoServicio {
    List<Medico> obtenerTodosMedicos();
    Optional<Medico> obtenerMedicoPorId(Long id);
    Medico guardarMedico(Medico medico);
    void eliminarMedico(Long id);
    public List<Medico> listarMedicos();
    boolean existsByEmail(String email);
}