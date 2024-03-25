package com.renatoganske.backendchallenge.adapters.out;

import com.renatoganske.backendchallenge.domain.dtos.inter.InterRequestTokenDto;
import com.renatoganske.backendchallenge.domain.dtos.inter.PaymentInterDto;
import com.renatoganske.backendchallenge.domain.dtos.inter.PaymentInterResponseDto;
import com.renatoganske.backendchallenge.domain.dtos.inter.TokenDto;
import feign.Headers;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "banco-inter", url = "${external.inter.url}")
public interface IInterClient {
    @Operation(summary = "Token", description = "Post para buscar token na API do Banco Inter.")
    @PostMapping("/oauth/v2/token")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    TokenDto getToken(@RequestBody InterRequestTokenDto request);

    @Operation(summary = "Calcular", description = "Post para realizar o cálculo")
    @PostMapping("/pix/v2/cob")
    PaymentInterResponseDto requestPayment(@RequestBody PaymentInterDto request);
}
