package com.examen2.examen.repositorio;

import com.examen2.examen.modelo.Bitacora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BitacoraRepositorio extends JpaRepository<Bitacora, Long> {
    public List<Bitacora> findAll();
}