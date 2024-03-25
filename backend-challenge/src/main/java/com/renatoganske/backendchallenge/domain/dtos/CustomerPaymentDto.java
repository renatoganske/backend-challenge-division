package com.renatoganske.backendchallenge.domain.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Esse DTO representa um objeto contendo o nome do cliente, o valor da sua compra e o link de pagamento")
public class CustomerPaymentDto {
    private String customer;
    private BigDecimal value;
    private String link;
}
