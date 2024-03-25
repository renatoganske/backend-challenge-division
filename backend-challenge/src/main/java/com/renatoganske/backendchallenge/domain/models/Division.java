package com.renatoganske.backendchallenge.domain.models;

import com.renatoganske.backendchallenge.domain.dtos.CustomerPaymentDto;
import com.renatoganske.backendchallenge.domain.dtos.DivisionResponse;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Division {
    private BigDecimal totalValueToPay;
    private List<CustomerPaymentDto> valuePerCustomer;

    public DivisionResponse toDivisionResponse() {
        return new DivisionResponse(
            this.valuePerCustomer
        );
    }
}
