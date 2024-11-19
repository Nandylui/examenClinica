package com.examen2.examen.servicio;

import com.examen2.examen.modelo.Medico;
import com.examen2.examen.repositorio.MedicoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoServicioImpl implements MedicoServicio {

    @Autowired
    private MedicoRepositorio medicoRepositorio;

    @Transactional(readOnly = true)
    @Override
    public List<Medico> obtenerTodosMedicos() {
        return medicoRepositorio.findAll();
    }

    @Override
    public Optional<Medico> obtenerMedicoPorId(Long id) {
        return medicoRepositorio.findById(id);
    }

    @Override
    public Medico guardarMedico(Medico medico) {
        return medicoRepositorio.save(medico);
    }

    @Override
    public void eliminarMedico(Long id) {
        medicoRepositorio.deleteById(id);
    }

    @Override
    public List<Medico> listarMedicos() {
        return medicoRepositorio.findAll();
    }

    @Override
    public boolean existsByEmail(String email) {
        // Implementa correctamente este método si es necesario
        return false;  // Placeholder; reemplázalo según tu lógica
    }

    // Métodos adicionales si los necesitas
    public List<Medico> listarTodoslosMedicos(String palabraClave) {
        if (palabraClave != null) {
            return medicoRepositorio.findByNombre(palabraClave);
        }
        return medicoRepositorio.findAll();
    }

    public Medico actualizarMedico(Medico medico) {
        return medicoRepositorio.save(medico);
    }
}
