package com.examen2.examen.repositorio;

import com.examen2.examen.modelo.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HorarioRepositorio extends JpaRepository<Horario, Long> {
}
