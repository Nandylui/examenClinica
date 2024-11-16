package com.examen2.examen.servicio;

import com.examen2.examen.modelo.Examen;
import com.examen2.examen.repositorio.ExamenRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamenServicioImpl implements ExamenServicio {

    private final ExamenRepositorio examenRepositorio;

    @Autowired
    public ExamenServicioImpl(ExamenRepositorio examenRepositorio) {
        this.examenRepositorio = examenRepositorio;
    }

    @Override
    public Examen guardarExamen(Examen examen) {
        return examenRepositorio.save(examen);
    }

    @Override
    public Examen obtenerExamenPorId(Long id) {
        return examenRepositorio.findById(id).orElse(null);
    }

    @Override
    public List<Examen> obtenerExamenesPorConsulta(Long consultaId) {
        return examenRepositorio.findByConsultaId(consultaId);
    }

    @Override
    public Examen actualizarExamen(Long id, Examen examen) {
        examen.setId(id);
        return examenRepositorio.save(examen);
    }

    @Override
    public void eliminarExamen(Long id) {
        examenRepositorio.deleteById(id);
    }

}
