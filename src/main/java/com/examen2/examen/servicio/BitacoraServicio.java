package com.examen2.examen.servicio;

import com.examen2.examen.modelo.Bitacora;
import com.examen2.examen.modelo.Usuario;
import com.examen2.examen.repositorio.BitacoraRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BitacoraServicio {

    @Autowired
    public BitacoraRepositorio bitacoraRepositorio;

    public List<Bitacora> listarBitacora(){
        return bitacoraRepositorio.findAll();
    }

    public void registrarBitacora(Bitacora bitacora){
        bitacora.setFecha(LocalDateTime.now());
        bitacoraRepositorio.save(bitacora);
    }

    public void registrarAccion(Usuario usuario, String accion, String dispositivo, String ip){
        Bitacora bitacora = new Bitacora();
        bitacora.setUsuario(usuario);
        bitacora.setEvento(accion);
        bitacora.setDetalle(dispositivo);
        bitacora.setIp(ip);
        bitacora.setFecha(LocalDateTime.now());
        bitacoraRepositorio.save(bitacora);
    }

}
