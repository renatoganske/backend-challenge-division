package com.renatoganske.backendchallenge.domain.dtos.inter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Esse DTO representa um objeto de response do Banco Inter.")
public class PaymentInterResponseDto {
    @JsonProperty("valor")
    private ValueDto value;
    private String location;
    @JsonProperty("solicitacaoPagador")
    private String payerRequest;
}
