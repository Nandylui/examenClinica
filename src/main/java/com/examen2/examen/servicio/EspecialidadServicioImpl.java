package com.examen2.examen.servicio;

import com.examen2.examen.modelo.Especialidad;
import com.examen2.examen.repositorio.EspecialidadRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspecialidadServicioImpl implements EspecialidadServicio{
    @Autowired
    private EspecialidadRepositorio especialidadRepositorio;

//    @Autowired
//    public EspecialidadServicioImpl(EspecialidadRepositorio especialidadRepositorio) {
//        this.especialidadRepositorio = especialidadRepositorio;
//    }

    @Override
    public Especialidad guardarEspecialidad(Especialidad especialidad) {
        return especialidadRepositorio.save(especialidad);
    }

    @Override
    public Optional<Especialidad> obtenerEspecialidadPorId(Long id) {
        return especialidadRepositorio.findById(id);
    }

    @Override
    public List<Especialidad> obtenerTodasLasEspecialidades() {
//        return especialidadRepositorio.findAll();
        return especialidadRepositorio.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public Especialidad actualizarEspecialidad(Long id, Especialidad especialidad) {
        especialidad.setId(id);
        return especialidadRepositorio.save(especialidad);
    }

    @Override
    public void eliminarEspecialidad(Long id) {
        especialidadRepositorio.deleteById(id);
    }
}