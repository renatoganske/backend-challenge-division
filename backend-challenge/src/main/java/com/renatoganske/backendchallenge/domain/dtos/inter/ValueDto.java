package com.renatoganske.backendchallenge.domain.dtos.inter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Esse DTO representa o valor para a Request na API do Banco Inter.")
public class ValueDto {
    private String original;
}
