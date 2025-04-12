package com.parameta.prueba.domain.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@Entity
@Table(name = "empleados")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombres", nullable = false, length = 150)
    private String nombres;

    @Column(name = "apellidos", nullable = false, length = 150)
    private String apellidos;

    @Column(name = "tipo_documento", nullable = false, length = 50)
    private String tipoDocumento;

    @Column(name = "numero_documento", nullable = false, length = 100)
    private String numeroDocumento;

    @Column(name = "fecha_nacimiento", nullable = false)
    private Date fechaNacimiento;

    @Column(name = "fecha_vinculacion", nullable = false)
    private Date fechaVinculacion;

    @Column(name = "cargo", nullable = false, length = 50)
    private String cargo;

    @Column(name = "salario", nullable = false)
    private Double salario;


    @Transient
    private TiempoRelativo edad;

    @Transient
    private TiempoRelativo antiguedad;

    public void validaciones() throws Exception{
        if (nombres == null || nombres.isBlank()) throw new IllegalArgumentException("Nombres requeridos");
        if (apellidos == null || apellidos.isBlank()) throw new IllegalArgumentException("Apellidos requeridos");
        if (tipoDocumento == null || tipoDocumento.isBlank()) throw new IllegalArgumentException("Tipo de documento requerido");
        if (numeroDocumento == null || numeroDocumento.isBlank()) throw new IllegalArgumentException("Numero de documento requerido");
        if (fechaNacimiento == null) throw new IllegalArgumentException("Fecha de nacimiento requerida");
        if (fechaVinculacion == null) throw  new IllegalArgumentException("Fecha de vinculacion requerida");
        if (cargo == null || cargo.isBlank()) throw new IllegalArgumentException("Cargo es requerido");
        if (salario == null || salario <= 0) throw new IllegalArgumentException("Salario es requerido");
        validarConsitenciaFechas();
        if (!esMayorDeEdad()) throw new IllegalArgumentException("Debe ser mayor de edad");
    }

    public void calcularTiempoRelativo() {
        this.edad = formatearFechasPeriod(Period.between(fromDateToLocalDate(fechaNacimiento), LocalDate.now()));
        this.antiguedad = formatearFechasPeriod(Period.between(fromDateToLocalDate(fechaVinculacion), LocalDate.now()));


    }

    private boolean esMayorDeEdad() {
        Integer anos = Period.between(fromDateToLocalDate(fechaNacimiento), LocalDate.now()).getYears();
        return  anos >= 18;

    }

    private TiempoRelativo formatearFechasPeriod(Period periodo) {

        Integer anos = periodo.getYears();
        Integer meses = periodo.getMonths();
        Integer dias = periodo.getDays();

        return new TiempoRelativo(anos, meses, dias);
    }

    private LocalDate fromDateToLocalDate (Date fecha) {
        return fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private void validarConsitenciaFechas() {
        if(this.fechaNacimiento.after(new Date())) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser mayor a la fecha actual");
        }
        if(this.fechaVinculacion.after(new Date())) {
            throw new IllegalArgumentException("La fecha de vinculacion no puede ser mayor igual a la fecha actual");
        }

    }
}
