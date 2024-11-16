package com.examen2.examen.modelo;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="pacientes", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "fechaNacimiento")
    private LocalDate fechaNacimiento;
    @Column(name = "sexo")
    private String sexo;
    @Column(name = "nroDocumento")
    private String nroDocumento;
    @Column(name = "email")
    private String email;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "fechaRegistro")
    private LocalDate fechaRegistro;


    //aqui es la relacion de paciente con histoaria
    @OneToOne(mappedBy = "paciente")
    private HistoriaClinica historiaClinica;

    //aqui es la relacion de paciente con ficha
    @OneToMany(mappedBy = "paciente")
    private List<Ficha> fichas;

    public Paciente(Long id, String nombre, String apellido, LocalDate fechaNacimiento, String sexo, String nroDocumento, String email, String telefono, String direccion, LocalDate fechaRegistro, HistoriaClinica historiaClinica, List<Ficha> fichas) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.nroDocumento = nroDocumento;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.fechaRegistro = fechaRegistro;
        this.historiaClinica = historiaClinica;
        this.fichas = fichas;
    }

    public Paciente(String nombre, String apellido, LocalDate fechaNacimiento, String sexo, String nroDocumento, String email, String telefono, String direccion, LocalDate fechaRegistro, HistoriaClinica historiaClinica, List<Ficha> fichas) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.nroDocumento = nroDocumento;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.fechaRegistro = fechaRegistro;
        this.historiaClinica = historiaClinica;
        this.fichas = fichas;
    }

    public Paciente() {
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

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public HistoriaClinica getHistoriaClinica() {
        return historiaClinica;
    }

    public void setHistoriaClinica(HistoriaClinica historiaClinica) {
        this.historiaClinica = historiaClinica;
    }

    public List<Ficha> getFichas() {
        return fichas;
    }

    public void setFichas(List<Ficha> fichas) {
        this.fichas = fichas;
    }

}
