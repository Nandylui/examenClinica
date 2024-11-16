package com.examen2.examen.servicio;

import com.examen2.examen.modelo.Horario;

import java.util.List;
import java.util.Optional;

public interface HorarioServicio {

    Horario guardarHorario(Horario horario);
    Optional<Horario> obtenerHorarioPorId(Long id);
    List<Horario> obtenerTodosLosHorarios();
    Horario actualizarHorario(Long id, Horario horario);
    void eliminarHorario(Long id);
}
