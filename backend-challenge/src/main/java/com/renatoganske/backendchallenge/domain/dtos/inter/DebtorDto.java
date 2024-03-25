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
@Schema(description = "Esse DTO representa o pagador para a Request na API do Banco Inter.")
public class DebtorDto {
    private String cpf;
    @JsonProperty("nome")
    private String name;

    public static String getCpfMock() {
        return "00000000000";
    }
}
