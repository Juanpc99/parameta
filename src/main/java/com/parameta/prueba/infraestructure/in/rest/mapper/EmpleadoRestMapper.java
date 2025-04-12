package com.parameta.prueba.infraestructure.in.rest.mapper;

import com.parameta.prueba.domain.model.Empleado;
import com.parameta.prueba.infraestructure.in.rest.dto.EmpleadoRequest;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class EmpleadoRestMapper {

    public Empleado toDomain(EmpleadoRequest dto) throws ParseException {
        if (dto == null) {
            return null;
        }

        Empleado empleado = new Empleado();

        empleado.setNombres(dto.getNombres());
        empleado.setApellidos(dto.getApellidos());
        empleado.setTipoDocumento(dto.getTipoDocumento());
        empleado.setNumeroDocumento(dto.getNumeroDocumento());
        empleado.setFechaNacimiento(toDate(dto.getFechaNacimiento()));
        empleado.setFechaVinculacion(toDate(dto.getFechaVinculacion()));
        empleado.setCargo(dto.getCargo());
        empleado.setSalario(dto.getSalario());

        return empleado;
    }

    private Date toDate(String fecha) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return formato.parse(fecha);
        } catch (ParseException e) {
            throw new ParseException("Formato invalido para la fecha " + fecha + ", considere utilizar el formato yyyy-MM-dd", 0);
        }
    }
}

