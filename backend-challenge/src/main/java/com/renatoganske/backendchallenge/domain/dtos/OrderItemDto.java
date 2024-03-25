package com.renatoganske.backendchallenge.domain.dtos;

import com.renatoganske.backendchallenge.domain.models.OrderItem;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Esse DTO representa um objeto no qual será informado o nome do cliente, o item pedido e seu respectivo valor.")
public class OrderItemDto {
    @NotEmpty(message = "Informe quem pediu esse item.")
    private String customer;
    @NotNull(message = "Informe o item pedido.")
    private String description;
    @NotNull(message = "Informe o valor do item.")
    private BigDecimal value;

    public OrderItem toEntity() {
        return OrderItem.builder()
            .customer(this.customer)
            .description(this.description)
            .value(this.value)
            .build();
    }
}
