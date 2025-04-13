
package com.parameta.prueba.soap.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "EmpleadoPort", targetNamespace = "http://127.0.0.1:8081/empleado")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface EmpleadoPort {


    /**
     * 
     * @param guardarEmpleadoRequest
     * @return
     *     returns com.parameta.soap.client.GuardarEmpleadoResponse
     */
    @WebMethod
    @WebResult(name = "guardarEmpleadoResponse", targetNamespace = "http://127.0.0.1/empleado", partName = "guardarEmpleadoResponse")
    public GuardarEmpleadoResponse guardarEmpleado(
        @WebParam(name = "guardarEmpleadoRequest", targetNamespace = "http://127.0.0.1/empleado", partName = "guardarEmpleadoRequest")
        GuardarEmpleadoRequest guardarEmpleadoRequest);

}
