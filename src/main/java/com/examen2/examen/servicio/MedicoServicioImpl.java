package com.examen2.examen.servicio;

import com.examen2.examen.modelo.Medico;
import com.examen2.examen.repositorio.MedicoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MedicoServicioImpl {
    @Autowired
    private MedicoRepositorio medicoRepositorio;

    @Transactional(readOnly = true)
    public List<Medico> findAll(){
        return (List<Medico>) medicoRepositorio.findAll();
    }

    public List<Medico> listarTodoslosMedicos(String palabraClave){
        if(palabraClave!=null){
            return medicoRepositorio.findByNombre(palabraClave);
        }
        return medicoRepositorio.findAll();
    }

    public List<Medico>listarTodosMedicos(){
        return medicoRepositorio.findAll();
    }

    public Medico guardarMedico(Medico medico){
        return medicoRepositorio.save(medico);
    }

    public Medico obtenerMedicoPorId(Long id){
        return medicoRepositorio.findById(id).get();
    }

    public Medico actualizarMedico(Medico medico){
        return medicoRepositorio.save(medico);
    }

    public void eliminarMedico(Long id){
        medicoRepositorio.deleteById(id);
    }

//    public boolean existsByEmail(String email) {
//        return medicoRepositorio.findByEmail(email);
//    }
}
