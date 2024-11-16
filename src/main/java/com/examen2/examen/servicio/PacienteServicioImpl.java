package com.examen2.examen.servicio;

import com.examen2.examen.modelo.Paciente;
import com.examen2.examen.repositorio.PacienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PacienteServicioImpl  implements  PacienteServicio{
    @Autowired
    private PacienteRepositorio pacienteRepositorio;

    @Transactional(readOnly = true)
    public List<Paciente> findAll() {
        return (List<Paciente>) pacienteRepositorio.findAll();
    }

    public List<Paciente> listarTodosLosPacientes(String palabraClave) {
        if (palabraClave != null) {
            return pacienteRepositorio.findByNombre(palabraClave);
        }
        return pacienteRepositorio.findAll();
    }

    public List<Paciente> listarTodosPacientes() {
        return pacienteRepositorio.findAll();
    }

    public Paciente guardarPaciente(Paciente paciente) {
        return pacienteRepositorio.save(paciente);
    }

    @Override
    public List<Paciente> obtenerTodosPacientes() {
        return List.of();
    }

    public Paciente obtenerPacientePorId(Long id) {
        return pacienteRepositorio.findById(id).get();
    }

    public Paciente actualizarPaciente(Paciente paciente) {
        return pacienteRepositorio.save(paciente);
    }

    public void eliminarPaciente(Long id) {
        pacienteRepositorio.deleteById(id);
    }

    @Override
    public List<Paciente> listarPacientes() {
        return List.of();
    }

    @Override
    public boolean existsByEmail(String email) {
        return false;
    }

}
