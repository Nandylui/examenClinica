package com.examen2.examen.controlador.dto;

import java.time.LocalDate;
import java.util.List;

public class HistoriaClinicaDTO {
    private Long id;
    private LocalDate fechaCreacion;
    private String observacion;
    private Long pacienteId; // Solo incluye el ID del paciente
    private List<Long> consultaIds; // Lista de IDs de consultas asociadas

    public HistoriaClinicaDTO() {
    }

    public HistoriaClinicaDTO(Long id, LocalDate fechaCreacion, String observacion, Long pacienteId, List<Long> consultaIds) {
        this.id = id;
        this.fechaCreacion = fechaCreacion;
        this.observacion = observacion;
        this.pacienteId = pacienteId;
        this.consultaIds = consultaIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public List<Long> getConsultaIds() {
        return consultaIds;
    }

    public void setConsultaIds(List<Long> consultaIds) {
        this.consultaIds = consultaIds;
    }

}
