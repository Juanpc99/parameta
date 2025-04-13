package com.parameta.prueba.infraestructure.out.soapserver;

import com.parameta.prueba.domain.model.Empleado;
import com.parameta.prueba.domain.ports.out.EmpleadoRepository;
import com.parameta.prueba.soap.client.GuardarEmpleadoRequest;
import com.parameta.prueba.soap.client.GuardarEmpleadoResponse;
import org.springframework.ws.server.endpoint.annotation.*;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;

@Endpoint
public class GuardarEmpleadoEndpoint {

    private static final String NAMESPACE_URI = "http://127.0.0.1/empleado";

    private final EmpleadoRepository repository;

    public GuardarEmpleadoEndpoint(EmpleadoRepository repository) {
        this.repository = repository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "guardarEmpleadoRequest")
    @ResponsePayload
    public GuardarEmpleadoResponse guardarEmpleado(@RequestPayload GuardarEmpleadoRequest request) {
        Empleado empleado = Empleado.builder()
                .nombres(request.getNombres())
                .apellidos(request.getApellidos())
                .tipoDocumento(request.getTipoDocumento())
                .numeroDocumento(request.getNumeroDocumento())
                .fechaNacimiento(convertirXmlGregorianCalendarADate(request.getFechaNacimiento()))
                .fechaVinculacion(convertirXmlGregorianCalendarADate(request.getFechaVinculacion()))
                .cargo(request.getCargo())
                .salario(request.getSalario())
                .build();

        repository.save(empleado);

        GuardarEmpleadoResponse response = new GuardarEmpleadoResponse();
        response.setMensaje("Empleado guardado vía SOAP correctamente");
        System.out.println(response.getMensaje());
        return response;
    }

    public Date convertirXmlGregorianCalendarADate(XMLGregorianCalendar calendar) {
        return calendar != null
                ? calendar.toGregorianCalendar().getTime()
                : null;
    }
}
