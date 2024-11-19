package com.examen2.examen.controlador.dto;

import java.time.LocalDate;

public class ConsultaDTO {
    private Long id;
    private LocalDate fecha;
    private String diagnostico;
    private String tratamiento;
    private Long medicoId;
    private Long historiaClinicaId;

    // Constructor vacío
    public ConsultaDTO() {
    }

    // Constructor completo
    public ConsultaDTO(Long id, LocalDate fecha, String diagnostico, String tratamiento, Long medicoId, Long historiaClinicaId) {
        this.id = id;
        this.fecha = fecha;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.medicoId = medicoId;
        this.historiaClinicaId = historiaClinicaId;
    }

    // Getters y Setters
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

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public Long getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(Long medicoId) {
        this.medicoId = medicoId;
    }

    public Long getHistoriaClinicaId() {
        return historiaClinicaId;
    }

    public void setHistoriaClinicaId(Long historiaClinicaId) {
        this.historiaClinicaId = historiaClinicaId;
    }
}
