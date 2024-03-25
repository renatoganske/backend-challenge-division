package com.renatoganske.backendchallenge.domain.models;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompleteOrder {
    private List<OrderItem> items;
    private BigDecimal deliveryTax;
    private BigDecimal discount;
    private BigDecimal additional;

    public BigDecimal totalValue() {
        return items.stream()
            .map(OrderItem::getValue)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal totalValueToPay() {
        return BigDecimal.ZERO.add(this.totalValue())
            .add(deliveryTax)
            .subtract(discount)
            .add(additional);
    }
}
