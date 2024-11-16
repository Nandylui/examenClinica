package com.examen2.examen.servicio;

import com.examen2.examen.modelo.Ficha;

import java.util.List;
import java.util.Optional;

public interface FichaServicio {
    // Obtener todas las fichas
    List<Ficha> obtenerTodasFichas();
    // Obtener una ficha por su ID
    Optional<Ficha> obtenerFichaPorId(Long id);
    // Guardar una nueva ficha
    Ficha guardarFicha(Ficha ficha);
    // Eliminar una ficha por su ID
    void eliminarFicha(Long id);
    // Listar todas las fichas (también puedes crear otro método con orden o filtrado si es necesario)
    List<Ficha> listarFichas();
    // Verificar si una ficha existe por algún atributo específico, como el 'estado'
    boolean existsByEstado(String estado);
}
