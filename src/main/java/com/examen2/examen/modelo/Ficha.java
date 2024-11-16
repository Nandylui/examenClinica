package com.examen2.examen.modelo;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "ficha")
public class Ficha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;
    private String estado;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "medico_id", nullable = false)
    private Medico medico;

    public Ficha(Long id, LocalDate fecha, String estado, Paciente paciente, Medico medico) {
        this.id = id;
        this.fecha = fecha;
        this.estado = estado;
        this.paciente = paciente;
        this.medico = medico;
    }

    public Ficha(LocalDate fecha, String estado, Paciente paciente, Medico medico) {
        this.fecha = fecha;
        this.estado = estado;
        this.paciente = paciente;
        this.medico = medico;
    }

    public Ficha() {
    }

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

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }
}
