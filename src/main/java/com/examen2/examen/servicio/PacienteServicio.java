package com.examen2.examen.servicio;

import com.examen2.examen.modelo.Paciente;

import java.util.List;

public interface PacienteServicio {
    //    List<PacienteDTO> listarPacientes();
//    PacienteDTO obtenerPacientePorId(Long id);
//    PacienteDTO crearPaciente(PacienteDTO pacienteDTO);
//    PacienteDTO actualizarPaciente(Long id, PacienteDTO pacienteDTO);
//    void eliminarPaciente(Long id);
    List<Paciente> obtenerTodosPacientes();
    Paciente obtenerPacientePorId(Long id);
    Paciente guardarPaciente(Paciente paciente);
    void eliminarPaciente(Long id);
    public List<Paciente> listarPacientes();
    boolean existsByEmail(String email);


}