package com.parameta.prueba.infraestructure.out;

import com.parameta.prueba.domain.model.Empleado;
import com.parameta.prueba.domain.ports.out.EmpleadoSoapPort;
import com.parameta.prueba.soap.client.*;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.GregorianCalendar;

@Repository
public class EmpleadoSoapAdapter implements EmpleadoSoapPort {

    private EmpleadoPort soapPort;

    @PostConstruct
    public void init() {
        try {
            URL wsdlUrl = getClass().getClassLoader().getResource("wsdl/empleado.wsdl");
            EmpleadoPortService service = new EmpleadoPortService(wsdlUrl);
            this.soapPort = service.getEmpleadoPortSoap11();
        } catch (Exception e) {
            throw new IllegalStateException("No se pudo cargar el WSDL desde recursos", e);
        }
    }

    @Override
    public void enviarEmpleado(Empleado empleado) {
        GuardarEmpleadoRequest request = new GuardarEmpleadoRequest();
        request.setNombres(empleado.getNombres());
        request.setApellidos(empleado.getApellidos());
        request.setTipoDocumento(empleado.getTipoDocumento());
        request.setNumeroDocumento(empleado.getNumeroDocumento());
        request.setFechaNacimiento(toXmlGregorianCalendar(empleado.getFechaNacimiento()));
        request.setFechaVinculacion(toXmlGregorianCalendar(empleado.getFechaVinculacion()));
        request.setCargo(empleado.getCargo());
        request.setSalario(empleado.getSalario());

        GuardarEmpleadoResponse response = soapPort.guardarEmpleado(request);
    }

    private LocalDate fromDateToLocalDate (Date fecha) {
        return fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
    private XMLGregorianCalendar toXmlGregorianCalendar(Date date) {
        if (date == null) return null;

        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);

        try {
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException("Error al convertir fecha a XMLGregorianCalendar", e);
        }
    }

}
