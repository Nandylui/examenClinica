package com.examen2.examen.servicio;

import com.examen2.examen.modelo.Consulta;

import java.util.List;

public interface ConsultaServicio {

    Consulta crearConsulta(Consulta consulta);

    Consulta obtenerConsultaPorId(Long id);

    List<Consulta> obtenerConsultasPorMedico(Long medicoId);

    List<Consulta> obtenerConsultasPorHistoriaClinica(Long historiaClinicaId);

//    List<Consulta> obtenerConsultasPorFecha(LocalDate FechaIni, LocalDate FechaFin);

    Consulta actualizarConsulta(Long id, Consulta consulta);

    void eliminarConsulta(Long id);
}
