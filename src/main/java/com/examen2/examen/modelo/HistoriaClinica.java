package com.examen2.examen.modelo;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "historiaclinica")
public class HistoriaClinica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    @Column(name = "observacion")
    private String observacion;

    //relacion entre paciente y historiaclinica
    @OneToOne
    @JoinColumn(name = "paciente_id", referencedColumnName = "id")
    private Paciente paciente;

    //relacion de historia clinica con consulta
    @OneToMany(mappedBy = "historiaClinica", cascade = CascadeType.ALL)
    private List<Consulta> consultas;

    public HistoriaClinica(Long id, LocalDate fechaCreacion, String observacion, Paciente paciente, List<Consulta> consultas) {
        this.id = id;
        this.fechaCreacion = fechaCreacion;
        this.observacion = observacion;
        this.paciente = paciente;
        this.consultas = consultas;
    }

    public HistoriaClinica(LocalDate fechaCreacion, String observacion, Paciente paciente, List<Consulta> consultas) {
        this.fechaCreacion = fechaCreacion;
        this.observacion = observacion;
        this.paciente = paciente;
        this.consultas = consultas;
    }

    public HistoriaClinica() {
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

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }
}
