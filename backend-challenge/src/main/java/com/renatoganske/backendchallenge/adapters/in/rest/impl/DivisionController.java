package com.renatoganske.backendchallenge.adapters.in.rest.impl;

import com.renatoganske.backendchallenge.domain.dtos.CompleteOrderDto;
import com.renatoganske.backendchallenge.domain.dtos.DivisionResponse;
import com.renatoganske.backendchallenge.adapters.in.rest.IDivisionController;
import com.renatoganske.backendchallenge.adapters.exceptions.BadRequestException;
import com.renatoganske.backendchallenge.services.DivisionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DivisionController implements IDivisionController {

    @Autowired
    private DivisionService service;

    public ResponseEntity<DivisionResponse> calculate(CompleteOrderDto completeOrderDto) {
        try {
            return ResponseEntity.ok(service.processDivision(completeOrderDto));
        } catch (BadRequestException ex) {
            log.error("Não foi possível fazer a divisão por ausencia de dados obrigatorios. Erro: {}", ex.getStackTrace());
            throw new BadRequestException(ex.getMessage(), ex.getError());
        }
    }
}
