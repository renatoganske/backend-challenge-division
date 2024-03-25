package com.renatoganske.backendchallenge.adapters.in.rest;

import com.renatoganske.backendchallenge.domain.dtos.CompleteOrderDto;
import com.renatoganske.backendchallenge.domain.dtos.DivisionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@Validated
@RequestMapping("/api")
@Tag(name = "Endpoint de Divisão", description = "Endpoint para realização de divisão de valores descontos e frete em compras em grupo")
public interface IDivisionController {

    @Operation(summary = "Calcular", description = "Post para realizar o cálculo")
    @PostMapping(value = "/calculations")
    ResponseEntity<DivisionResponse> calculate(@RequestBody @Valid CompleteOrderDto completeOrderDto);
}
