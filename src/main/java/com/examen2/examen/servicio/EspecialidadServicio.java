package com.examen2.examen.servicio;

import com.examen2.examen.modelo.Especialidad;

import java.util.List;
import java.util.Optional;

public interface EspecialidadServicio {
    Especialidad guardarEspecialidad(Especialidad especialidad);
    Optional<Especialidad> obtenerEspecialidadPorId(Long id);
    List<Especialidad> obtenerTodasLasEspecialidades();
    Especialidad actualizarEspecialidad(Long id, Especialidad especialidad);
    void eliminarEspecialidad(Long id);
}