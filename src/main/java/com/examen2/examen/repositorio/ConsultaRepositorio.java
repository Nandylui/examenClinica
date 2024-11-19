package com.examen2.examen.repositorio;

import com.examen2.examen.modelo.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ConsultaRepositorio extends JpaRepository<Consulta, Long> {
    List<Consulta> findByMedicoId(Long medicoId);
    List<Consulta> findByHistoriaClinicaId(Long historiaClinicaId);
//    List<Consulta> findByFecha(LocalDate FechaIni, LocalDate FechaFin);
    List<Consulta> findByFechaBetween(LocalDate startDate, LocalDate endDate);
}
