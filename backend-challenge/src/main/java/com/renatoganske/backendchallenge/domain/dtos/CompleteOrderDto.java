package com.renatoganske.backendchallenge.domain.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.renatoganske.backendchallenge.domain.models.CompleteOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Esse DTO representa um pedido completo.")
public class CompleteOrderDto {

    @Schema(description = "Itens do pedido.")
    private List<OrderItemDto> items;
    @Schema(description = "Taxa de entrega.")
    private BigDecimal deliveryTax;
    @Schema(description = "Valor do desconto.")
    private BigDecimal discount;
    private BigDecimal additional;

    public CompleteOrder toEntity() {
        return CompleteOrder.builder()
            .items(this.getItems()
                .stream()
                .map(OrderItemDto::toEntity)
                .collect(Collectors.toList()))
            .deliveryTax(this.deliveryTax)
            .discount(this.discount)
            .additional(this.additional)
            .build();
    }
}
