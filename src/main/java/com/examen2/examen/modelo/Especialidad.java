package com.examen2.examen.modelo;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "especialidad")
public class Especialidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;

    // Relación con Medico (Uno a Muchos)
    @OneToMany(mappedBy = "especialidad", cascade = CascadeType.ALL)
    private List<Medico> medicos;

    @Override
    public String toString() {
        return nombre;  // O cualquier otro atributo que desees mostrar
    }

    public Especialidad(Long id, String nombre, String descripcion, List<Medico> medicos) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.medicos = medicos;
    }

    public Especialidad(String nombre, String descripcion, List<Medico> medicos) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.medicos = medicos;
    }

    public Especialidad() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Medico> getMedicos() {
        return medicos;
    }

    public void setMedicos(List<Medico> medicos) {
        this.medicos = medicos;
    }
}
