package com.renatoganske.backendchallenge.domain.models;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    private String customer;
    private String description;
    private BigDecimal value;
}
