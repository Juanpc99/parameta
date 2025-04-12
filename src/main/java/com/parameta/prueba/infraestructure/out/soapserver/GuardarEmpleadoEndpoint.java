package com.parameta.prueba.infraestructure.out.soapserver;

import com.parameta.prueba.domain.model.Empleado;
import com.parameta.prueba.domain.ports.out.EmpleadoRepository;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class GuardarEmpleadoEndpoint {

    private static final String MENSAJE = "Empleado guardado vía SOAP";

    private final EmpleadoRepository repository;

    public GuardarEmpleadoEndpoint(EmpleadoRepository repository) {
        this.repository = repository;
    }

    private static final String NAMESPACE_URI = "http://127.0.0.1/empleado";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "guardarEmpleadoRequest")
    @ResponsePayload
    public void guardarEmpleado(@RequestPayload Empleado empleado) {
        repository.save(empleado);
    }
}