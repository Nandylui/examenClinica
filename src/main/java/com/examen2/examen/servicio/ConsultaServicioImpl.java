package com.examen2.examen.servicio;

import com.examen2.examen.modelo.Consulta;
import com.examen2.examen.repositorio.ConsultaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultaServicioImpl implements ConsultaServicio{

    private ConsultaRepositorio consultaRepositorio;

    @Autowired
    public ConsultaServicioImpl(ConsultaRepositorio consultaRepositorio) {
        this.consultaRepositorio = consultaRepositorio;
    }

    @Override
    public Consulta crearConsulta(Consulta consulta) {
        return consultaRepositorio.save(consulta);
    }

    @Override
    public Consulta obtenerConsultaPorId(Long id) {
        return consultaRepositorio.findById(id).orElse(null);
    }

    @Override
    public List<Consulta> obtenerConsultasPorMedico(Long medicoId) {
        return consultaRepositorio.findByMedicoId(medicoId);
    }

    @Override
    public List<Consulta> obtenerConsultasPorHistoriaClinica(Long historiaClinicaId) {
        return consultaRepositorio.findByHistoriaClinicaId(historiaClinicaId);
    }

//    @Override
//    public List<Consulta> obtenerConsultasPorFecha(LocalDate FechaIni, LocalDate FechaFin) {
//        return consultaRepositorio.findByFechaBetween(startDate, endDate);
//    }

    @Override
    public Consulta actualizarConsulta(Long id, Consulta consulta) {
        Optional<Consulta> consultaExistente = consultaRepositorio.findById(id);
        if (consultaExistente.isPresent()) {
            Consulta actualizarConsulta = consultaExistente.get();
            actualizarConsulta.setFecha(consulta.getFecha());
            actualizarConsulta.setDiagnostico(consulta.getDiagnostico());
            actualizarConsulta.setTratamiento(consulta.getTratamiento());
            actualizarConsulta.setMedico(consulta.getMedico());
            actualizarConsulta.setHistoriaClinica(consulta.getHistoriaClinica());
            return consultaRepositorio.save(actualizarConsulta);
        }
        return null;
    }

    @Override
    public void eliminarConsulta(Long id) {
        consultaRepositorio.deleteById(id);
    }
}
