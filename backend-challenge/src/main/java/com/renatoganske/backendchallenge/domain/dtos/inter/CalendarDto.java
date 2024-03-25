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
@Schema(description = "Esse DTO representa a expiração do link de pagamento para a Request na API do Banco Inter.")
public class CalendarDto {
    @JsonProperty("expiracao")
    private Integer expiration;
}
