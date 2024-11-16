package com.examen2.examen.repositorio;

import com.examen2.examen.modelo.Ficha;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FichaRepositorio extends JpaRepository<Ficha, Long> {
    // Buscar ficha por algún atributo, por ejemplo, 'estado'
    List<Ficha> findByEstado(String estado);
    // Buscar todas las fichas
    List<Ficha> findAll();
    // Buscar ficha por ID (esto ya lo proporciona JpaRepository por defecto)
    Optional<Ficha> findById(Long id);
    // Buscar por algún atributo como 'nombre', de forma parcial e ignorando mayúsculas/minúsculas
    List<Ficha> findByEstadoContainingIgnoreCase(String estado, Sort sort);

    // Si quieres añadir una búsqueda por paciente o médico, podrías hacerlo también de manera similar:
    // List<Ficha> findByPaciente(Paciente paciente);
    // List<Ficha> findByMedico(Medico medico);
}
