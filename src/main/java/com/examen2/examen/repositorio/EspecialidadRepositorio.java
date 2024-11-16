package com.examen2.examen.repositorio;

import com.examen2.examen.modelo.Especialidad;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EspecialidadRepositorio extends JpaRepository<Especialidad, Long> {
    //    Método para buscar especialidades por nombre, ignorando mayúsculas y minúsculas
    List<Especialidad> findByNombreContainingIgnoreCase(String nombre, Sort sort);

    //    Método para buscar por nombre de especialidad
    Especialidad findByNombre(String nombre);
}
