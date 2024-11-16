package com.examen2.examen.repositorio;

import com.examen2.examen.modelo.Examen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamenRepositorio extends JpaRepository<Examen, Long> {
    List<Examen> findByConsultaId(Long consultaId);
}
