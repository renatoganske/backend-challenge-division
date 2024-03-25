package com.renatoganske.backendchallenge.fixtures;

import com.renatoganske.backendchallenge.domain.dtos.CompleteOrderDto;
import com.renatoganske.backendchallenge.domain.dtos.OrderItemDto;

import java.math.BigDecimal;
import java.util.ArrayList;

public class CompleteOrderDtoMock {

    public static CompleteOrderDto get() {
        CompleteOrderDto completeOrderDto = CompleteOrderDto.builder()
            .items(new ArrayList<>())
            .deliveryTax(BigDecimal.valueOf(8))
            .discount(BigDecimal.valueOf(20))
            .additional(BigDecimal.valueOf(0))
            .build();

        OrderItemDto hamburguer = OrderItemDto.builder()
            .customer("Artur")
            .description("Hamburger")
            .value(BigDecimal.valueOf(40))
            .build();

        OrderItemDto dessert = OrderItemDto.builder()
            .customer("Maria")
            .description("Dessert")
            .value(BigDecimal.valueOf(2))
            .build();

        OrderItemDto sandwich = OrderItemDto.builder()
            .customer("João")
            .description("Sandwich")
            .value(BigDecimal.valueOf(8))
            .build();

        completeOrderDto.getItems()
            .add(hamburguer);
        completeOrderDto.getItems()
            .add(dessert);
        completeOrderDto.getItems()
            .add(sandwich);

        return completeOrderDto;
    }

    public static CompleteOrderDto withoutDeliveryTax() {
        CompleteOrderDto completeOrderDto = CompleteOrderDto.builder()
                .items(new ArrayList<>())
                .deliveryTax(BigDecimal.valueOf(8))
                .discount(BigDecimal.valueOf(20))
                .additional(BigDecimal.valueOf(0))
                .build();

        OrderItemDto hamburguer = OrderItemDto.builder()
                .customer("Artur")
                .description("Hamburger")
                .value(BigDecimal.valueOf(40))
                .build();

        OrderItemDto dessert = OrderItemDto.builder()
                .customer("Maria")
                .description("Dessert")
                .value(BigDecimal.valueOf(2))
                .build();

        OrderItemDto sandwich = OrderItemDto.builder()
                .customer("João")
                .description("Sandwich")
                .value(BigDecimal.valueOf(8))
                .build();

        completeOrderDto.getItems()
                .add(hamburguer);
        completeOrderDto.getItems()
                .add(dessert);
        completeOrderDto.getItems()
                .add(sandwich);

        return completeOrderDto;
    }
}
