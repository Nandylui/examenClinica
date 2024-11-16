package com.examen2.examen.repositorio;

import com.examen2.examen.modelo.Paciente;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PacienteRepositorio extends JpaRepository<Paciente, Long> {
    List<Paciente> findByNombre(String palabraClave);
    public List<Paciente> findAll();
    public Paciente findByEmail(String email);

    // Método para buscar pacientes por nombre con criterio de orden
    List<Paciente> findByNombreContainingIgnoreCase(String nombre, Sort sort);
}
