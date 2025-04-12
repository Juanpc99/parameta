package com.parameta.prueba.domain.ports.in;

import com.parameta.prueba.domain.model.Empleado;

public interface RegistrarEmpleadosUseCase {

    Empleado registrarEmpleado(Empleado empleado) throws Exception;
}
