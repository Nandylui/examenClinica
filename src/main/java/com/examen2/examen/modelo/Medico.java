package com.examen2.examen.modelo;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "medico")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "sexo")
    private String sexo;
    @Column(name = "email")
    private String email;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "nroMatricula")
    private String nromatricula;

    @ManyToOne
    @JoinColumn(name = "especialidad_id")
    private Especialidad especialidad;

    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
    private List<Horario> horarios;

    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
    private List<Ficha> fichas;

    public Medico(Long id, String nombre, String apellido, String sexo, String email, String telefono, String nromatricula, Especialidad especialidad, List<Horario> horarios, List<Ficha> fichas) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;
        this.email = email;
        this.telefono = telefono;
        this.nromatricula = nromatricula;
        this.especialidad = especialidad;
        this.horarios = horarios;
        this.fichas = fichas;
    }

    public Medico(String nombre, String apellido, String sexo, String email, String telefono, String nromatricula, Especialidad especialidad, List<Horario> horarios, List<Ficha> fichas) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;
        this.email = email;
        this.telefono = telefono;
        this.nromatricula = nromatricula;
        this.especialidad = especialidad;
        this.horarios = horarios;
        this.fichas = fichas;
    }

    public Medico() {
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNromatricula() {
        return nromatricula;
    }

    public void setNromatricula(String nromatricula) {
        this.nromatricula = nromatricula;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    public List<Ficha> getFichas() {
        return fichas;
    }

    public void setFichas(List<Ficha> fichas) {
        this.fichas = fichas;
    }

}
