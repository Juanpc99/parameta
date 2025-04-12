package com.parameta.prueba.application.service;

import com.parameta.prueba.domain.model.Empleado;
import com.parameta.prueba.domain.ports.in.RegistrarEmpleadosUseCase;
import com.parameta.prueba.domain.ports.out.EmpleadoRepository;
import com.parameta.prueba.domain.ports.out.EmpleadoSoapPort;
import org.springframework.stereotype.Service;


@Service
public class EmpleadoService implements RegistrarEmpleadosUseCase {

    private final EmpleadoSoapPort soapPort;

    public EmpleadoService(EmpleadoSoapPort soapPort){
        this.soapPort = soapPort;
    }

    @Override
    public Empleado registrarEmpleado(Empleado empleado) throws Exception{

        empleado.validaciones();
        empleado.calcularEdad();
        empleado.calcularAntiguedad();

        soapPort.enviarEmpleado(empleado);

        return empleado;
    }
}
