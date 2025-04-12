package com.parameta.prueba.infraestructure.in.rest.mapper;

import com.parameta.prueba.domain.model.Empleado;
import com.parameta.prueba.infraestructure.in.rest.dto.EmpleadoRequest;
import org.springframework.stereotype.Component;

@Component
public class EmpleadoRestMapper {

    public Empleado toDomain(EmpleadoRequest dto) {
        if (dto == null) {
            return null;
        }

        Empleado empleado = new Empleado();

        empleado.setNombres(dto.getNombres());
        empleado.setApellidos(dto.getApellidos());
        empleado.setTipoDocumento(dto.getTipoDocumento());
        empleado.setNumeroDocumento(dto.getNumeroDocumento());
        empleado.setFechaNacimiento(dto.getFechaNacimiento());
        empleado.setFechaVinculacion(dto.getFechaVinculacion());
        empleado.setCargo(dto.getCargo());
        empleado.setSalario(dto.getSalario());

        return empleado;
    }
}
