package com.examen2.examen.controlador.dto;

public class MedicoDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String sexo;
    private String email;
    private String telefono;
    private String nromatricula;
    private Long especialidadId; // Ahora solo se maneja el ID

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

    public Long getEspecialidadId() {
        return especialidadId; // Devuelve el ID
    }

    public void setEspecialidadId(Long especialidadId) {
        this.especialidadId = especialidadId; // Recibe solo el ID
    }
}
