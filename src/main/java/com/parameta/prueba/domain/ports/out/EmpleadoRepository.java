package com.parameta.prueba.domain.ports.out;

import com.parameta.prueba.domain.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
}
