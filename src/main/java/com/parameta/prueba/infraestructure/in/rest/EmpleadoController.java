package com.parameta.prueba.infraestructure.in.rest;

import com.parameta.prueba.domain.model.Empleado;
import com.parameta.prueba.domain.ports.in.RegistrarEmpleadosUseCase;
import com.parameta.prueba.infraestructure.in.rest.dto.EmpleadoRequest;
import com.parameta.prueba.infraestructure.in.rest.mapper.EmpleadoRestMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EmpleadoController {

    private final RegistrarEmpleadosUseCase useCase;
    private final EmpleadoRestMapper mapper;

    public EmpleadoController (RegistrarEmpleadosUseCase useCase,
                                EmpleadoRestMapper mapper
    ) {
        this.useCase = useCase;
        this.mapper = mapper;
    }

    @GetMapping("/save")
    public ResponseEntity<?> guardarEmpleado(@ModelAttribute EmpleadoRequest empleado) {
        Empleado fromDto = mapper.toDomain(empleado);
        try {
            return ResponseEntity.ok(useCase.registrarEmpleado(fromDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
