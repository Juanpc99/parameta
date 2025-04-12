package com.parameta.prueba.infraestructure.out;

import com.parameta.prueba.domain.model.Empleado;
import com.parameta.prueba.domain.ports.out.EmpleadoSoapPort;
import com.parameta.prueba.infraestructure.out.soapserver.GuardarEmpleadoEndpoint;
import org.springframework.stereotype.Component;

@Component
public class EmpleadoSoapAdapter implements EmpleadoSoapPort {

    private final GuardarEmpleadoEndpoint endpoint;

    public EmpleadoSoapAdapter(GuardarEmpleadoEndpoint endpoint) {
        this.endpoint = endpoint;
    }


    @Override
    public void enviarEmpleado(Empleado empleado) {
        endpoint.guardarEmpleado(empleado);
    }
}
