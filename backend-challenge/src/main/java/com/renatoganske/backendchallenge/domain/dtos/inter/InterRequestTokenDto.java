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
@Schema(description = "Esse DTO representa um objeto para solicitar um token.")
public class InterRequestTokenDto {

    @JsonProperty("client_id")
    private String clientId;
    @JsonProperty("client_secret")
    private String clientSecret;
    @JsonProperty("scope") //scope=boleto-cobranca.read
    private String scope;
    @JsonProperty("grant_type") //&grant_type=client_credentials
    private String grantType;

}
