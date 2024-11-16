package com.examen2.examen.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "examen")
public class Examen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoExamen;
    private String resultado;

    // Relación con Consulta
    @ManyToOne
    @JoinColumn(name = "consulta_id")
    private Consulta consulta;

    public Examen(Long id, String tipoExamen, String resultado, Consulta consulta) {
        this.id = id;
        this.tipoExamen = tipoExamen;
        this.resultado = resultado;
        this.consulta = consulta;
    }

    public Examen(String tipoExamen, String resultado, Consulta consulta) {
        this.tipoExamen = tipoExamen;
        this.resultado = resultado;
        this.consulta = consulta;
    }

    public Examen() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoExamen() {
        return tipoExamen;
    }

    public void setTipoExamen(String tipoExamen) {
        this.tipoExamen = tipoExamen;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }
}
