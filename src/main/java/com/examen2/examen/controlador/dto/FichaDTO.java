package com.examen2.examen.controlador.dto;

import java.time.LocalDate;

public class FichaDTO {
    private Long id;
    private LocalDate fecha;
    private String estado;
    private Long pacienteId;
    private Long medicoId;

    public FichaDTO() {
    }

    public FichaDTO(Long id, LocalDate fecha, String estado, Long pacienteId, Long medicoId) {
        this.id = id;
        this.fecha = fecha;
        this.estado = estado;
        this.pacienteId = pacienteId;
        this.medicoId = medicoId;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Long getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(Long medicoId) {
        this.medicoId = medicoId;
    }
}
