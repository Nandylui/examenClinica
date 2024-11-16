package com.examen2.examen.servicio;

import com.examen2.examen.modelo.Ficha;
import com.examen2.examen.repositorio.FichaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FichaServicioImpl implements FichaServicio{
    @Autowired
    private FichaRepositorio fichaRepositorio;

    // Obtener todas las fichas
    @Transactional(readOnly = true)
    public List<Ficha> obtenerTodasFichas() {
        return fichaRepositorio.findAll();
    }

    // Obtener una ficha por su ID
    @Transactional(readOnly = true)
    public Optional<Ficha> obtenerFichaPorId(Long id) {
        return fichaRepositorio.findById(id);
    }

    // Guardar una nueva ficha
    public Ficha guardarFicha(Ficha ficha) {
        return fichaRepositorio.save(ficha);
    }

    // Eliminar una ficha por su ID
    public void eliminarFicha(Long id) {
        fichaRepositorio.deleteById(id);
    }

    // Listar todas las fichas
    @Transactional(readOnly = true)
    public List<Ficha> listarFichas() {
        return fichaRepositorio.findAll();
    }

    @Override
    public boolean existsByEstado(String estado) {
        return false;
    }

//    // Verificar si una ficha existe por un estado específico
//    @Transactional(readOnly = true)
//    public boolean existsByEstado(String estado) {
//        return fichaRepositorio.existsByEstado(estado);
//    }
}
