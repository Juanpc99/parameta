package com.parameta.prueba.domain.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;

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
    private LocalDate fechaNacimiento;

    @Column(name = "fecha_vinculacion", nullable = false)
    private LocalDate fechaVinculacion;

    @Column(name = "cargo", nullable = false, length = 50)
    private String cargo;

    @Column(name = "salario", nullable = false)
    private Double salario;


    @Transient
    private String edad;

    @Transient
    private String antiguedad;

    public void validaciones() throws Exception{
        if (nombres == null || nombres.isBlank()) throw new IllegalArgumentException("Nombres requeridos");
        if (apellidos == null || apellidos.isBlank()) throw new IllegalArgumentException("Apellidos requeridos");
        if (tipoDocumento == null || tipoDocumento.isBlank()) throw new IllegalArgumentException("Tipo de documento requerido");
        if (numeroDocumento == null || numeroDocumento.isBlank()) throw new IllegalArgumentException("Numero de documento requerido");
        if (fechaNacimiento == null) throw new IllegalArgumentException("Fecha de nacimiento requerida");
        if (fechaVinculacion == null) throw  new IllegalArgumentException("Fecha de vinculacion requerida");
        if (cargo == null || cargo.isBlank()) throw new IllegalArgumentException("Cargo es requerido");
        if (salario == null || salario <= 0) throw new IllegalArgumentException("Salario es requerido");

        if (!esMayorDeEdad()) throw new IllegalArgumentException("Debe ser mayor de edad");
    }

    private boolean esMayorDeEdad() {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears() >= 18;

    }

    public void calcularEdad() {
        this.edad = formatearFechasPeriod(Period.between(fechaNacimiento, LocalDate.now()));
    }

    public void calcularAntiguedad() {
        this.antiguedad = formatearFechasPeriod(Period.between(fechaVinculacion, LocalDate.now()));
    }

    private String formatearFechasPeriod(Period periodo) {
        return periodo.getYears() + " años, " + periodo.getMonths() + " meses, " + periodo.getDays() + " dias";
    }
}
