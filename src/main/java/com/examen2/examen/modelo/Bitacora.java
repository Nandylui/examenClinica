package com.examen2.examen.modelo;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name ="bitacora")
public class Bitacora {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ip;
    private LocalDateTime fecha;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    private String evento;
    private String detalle;

    public Bitacora(Long id, String ip, LocalDateTime fecha, Usuario usuario, String evento, String detalle) {
        this.id = id;
        this.ip = ip;
        this.fecha = fecha;
        this.usuario = usuario;
        this.evento = evento;
        this.detalle = detalle;
    }


    public Bitacora(String ip, LocalDateTime fecha, Usuario usuario, String evento, String detalle) {
        this.ip = ip;
        this.fecha = fecha;
        this.usuario = usuario;
        this.evento = evento;
        this.detalle = detalle;
    }

    public Bitacora() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
}
