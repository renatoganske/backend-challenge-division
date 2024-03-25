package com.renatoganske.backendchallenge.services;

import com.renatoganske.backendchallenge.adapters.out.IInterClient;
import com.renatoganske.backendchallenge.domain.dtos.CustomerPaymentDto;
import com.renatoganske.backendchallenge.domain.dtos.inter.*;
import com.renatoganske.backendchallenge.adapters.exceptions.PaymentException;
import com.renatoganske.backendchallenge.adapters.exceptions.TokenNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentService {

    @Autowired
    private IInterClient interClient;

    @Value("${external.inter.client-id}")
    private String clientId;

    @Value("${external.inter.client-secret}")
    private String clientSecret;

    @Value("${external.inter.scope}")
    private String scope;

    @Value("${external.inter.grant-type}")
    private String grantType;

    @Value("${external.inter.key-pix}")
    private String keyPix;

    public TokenDto getToken() {
        log.info("Buscando o token da api do inter");
        try {
            final InterRequestTokenDto request = InterRequestTokenDto.builder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .grantType(grantType)
                .scope(scope)
                .build();
            return interClient.getToken(request);
        } catch (final Exception ex) {
            log.error("Houve um erro ao buscar o token!", ex);
            throw new TokenNotFoundException(ex.getMessage());
        }
    }

    public PaymentInterResponseDto paymentRealize(final CustomerPaymentDto customerPaymentDto) {
        log.info("Realizando a cobrança no Banco Inter. Input: {}", customerPaymentDto);
        try {
            final TokenDto tokenDto = getToken();
            return requestPayment(customerPaymentDto);
        } catch (final Exception ex) {
            log.error("Houve um erro ao realizar o pagamento!", ex);
            throw new PaymentException("Houve um erro ao realizar o pagamento!", ex.getMessage());
        }
    }

    private PaymentInterResponseDto requestPayment(final CustomerPaymentDto customerPaymentDto) {
        log.info("Realizando a cobrança no Banco Inter para o  {} no valor de {}", customerPaymentDto.getCustomer(),
            customerPaymentDto.getValue());
        final PaymentInterDto request = PaymentInterDto.builder()
            .calendar(CalendarDto.builder()
                .expiration(86400)
                .build())
            .debtor(DebtorDto.builder()
                .name(customerPaymentDto.getCustomer())
                .cpf(DebtorDto.getCpfMock())
                .build())
            .value(ValueDto.builder()
                .original(customerPaymentDto.getValue()
                    .toString())
                .build())
            .key(keyPix)
            .build();
        return interClient.requestPayment(request);
    }
}
