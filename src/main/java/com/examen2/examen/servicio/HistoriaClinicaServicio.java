package com.examen2.examen.servicio;

import com.examen2.examen.modelo.HistoriaClinica;

import java.util.List;
import java.util.Optional;

public interface HistoriaClinicaServicio {

    HistoriaClinica guardarHistoriaClinica(HistoriaClinica historiaClinica);
    Optional<HistoriaClinica> obtenerHistoriaClinicaPorId(Long id);
    Optional<HistoriaClinica> obtenerHistoriaClinicaPorPacienteId(Long pacienteId);
    List<HistoriaClinica> obtenerTodasLasHistoriasClinicas();
}
