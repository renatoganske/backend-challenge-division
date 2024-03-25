package com.renatoganske.backendchallenge.domain.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Esse DTO representa uma lista contendo os clientes e seus respectivos valores.")
public class DivisionResponse {
    private List<CustomerPaymentDto> valuePerCustomer;
}
