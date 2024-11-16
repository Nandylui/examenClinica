package com.examen2.examen.repositorio;

import com.examen2.examen.modelo.Medico;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
//@Repository
public interface MedicoRepositorio extends JpaRepository<Medico, Long> {
    List<Medico> findByNombre(String palabraclave);
    public List<Medico>findAll();
    public Medico findByEmail(String mail);

    //    hemos agregado esto
    List<Medico> findByNombreContainingIgnoreCase(String nombre, Sort sort);

}
