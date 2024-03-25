package com.renatoganske.backendchallenge.domain.dtos.inter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Esse DTO representa um objeto para criar o link de pagamento no Banco Inter.")
public class PaymentInterDto {
    @JsonProperty("calendario")
    private CalendarDto calendar;
    @JsonProperty("devedor")
    private DebtorDto debtor;
    @JsonProperty("valor")
    private ValueDto value;
    private String key;
}
