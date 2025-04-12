package com.parameta.prueba.domain.ports.out;

import com.parameta.prueba.domain.model.Empleado;

public interface EmpleadoSoapPort {

    void enviarEmpleado(Empleado empleado);
}
