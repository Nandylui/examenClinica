package com.examen2.examen.servicio;

import com.examen2.examen.modelo.Horario;
import com.examen2.examen.repositorio.HorarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HorarioServicioImpl implements HorarioServicio{

    private HorarioRepositorio horarioRepositorio;

    @Autowired
    public HorarioServicioImpl(HorarioRepositorio horarioRepositorio) {
        this.horarioRepositorio = horarioRepositorio;
    }

    @Override
    public Horario guardarHorario(Horario horario) {
        return horarioRepositorio.save(horario);
    }

    @Override
    public Optional<Horario> obtenerHorarioPorId(Long id) {
        return horarioRepositorio.findById(id);
    }

    @Override
    public List<Horario> obtenerTodosLosHorarios() {
        return horarioRepositorio.findAll();
    }

    @Override
    public Horario actualizarHorario(Long id, Horario horario) {
        horario.setId(id);
        return horarioRepositorio.save(horario);
    }

    @Override
    public void eliminarHorario(Long id) {
        horarioRepositorio.deleteById(id);
    }
}
