package com.parameta.prueba.infraestructure.in.rest.dto;



import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EmpleadoRequest {

    private String nombres;
    private String apellidos;
    private String tipoDocumento;
    private String numeroDocumento;
    private String fechaNacimiento;
    private String fechaVinculacion;
    private String cargo;
    private Double salario;

}
