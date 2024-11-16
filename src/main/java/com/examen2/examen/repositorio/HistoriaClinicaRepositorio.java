package com.examen2.examen.repositorio;

import com.examen2.examen.modelo.HistoriaClinica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HistoriaClinicaRepositorio extends JpaRepository<HistoriaClinica, Long> {
    // metodo para encontrar la historia clinica por el ID del paciente
    Optional<HistoriaClinica> findByPacienteId(Long pacienteId);

    //metodo para verificar si una historia clinica ya existe para un paciente especifico
    boolean existsByPacienteId(Long pacienteId);

}
