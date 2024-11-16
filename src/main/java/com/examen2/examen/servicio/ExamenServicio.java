package com.examen2.examen.servicio;

import com.examen2.examen.modelo.Examen;

import java.util.List;

public interface ExamenServicio {
    Examen guardarExamen(Examen examen);
    Examen obtenerExamenPorId(Long id);
    List<Examen> obtenerExamenesPorConsulta(Long consultaId);
    Examen actualizarExamen(Long id, Examen examen);
    void eliminarExamen(Long id);

}