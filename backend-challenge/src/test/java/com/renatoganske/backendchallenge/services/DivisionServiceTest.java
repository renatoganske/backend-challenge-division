package com.renatoganske.backendchallenge.services;

import com.renatoganske.backendchallenge.domain.dtos.DivisionResponse;
import com.renatoganske.backendchallenge.fixtures.CompleteOrderDtoMock;
import com.renatoganske.backendchallenge.domain.models.CompleteOrder;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;

@SpringBootTest()
@WebAppConfiguration
class DivisionServiceTest {

    @Autowired
    private DivisionService service;

    @Test
    void whenProcessDivisionShouldReturnSuccess() {
        DivisionResponse division = service.processDivision(CompleteOrderDtoMock.get());
        assertThat(division.getValuePerCustomer()
            .size(), is(3));
        assertThat(division.getValuePerCustomer()
            .get(0)
            .getValue(), is(BigDecimal.valueOf(6.08)));
    }

    @Test
    void whenCalculateValueByCustomerShouldReturnSuccess() {
        CompleteOrder order = CompleteOrderDtoMock.get()
            .toEntity();
        CompleteOrder completeOrder = new CompleteOrder(
            order.getItems(),
            order.getDeliveryTax(),
            order.getDiscount(),
            order.getAdditional());
        var customer = order.getItems().get(0).getCustomer();
        var totalValueToPay = order.totalValueToPay();

        BigDecimal valueByPerson = service.calculateValueByCustomer(customer,
                                                                    totalValueToPay,
                                                                    completeOrder);

        assertThat(valueByPerson, Matchers.comparesEqualTo(BigDecimal.valueOf(30.40)));
    }

    @Test
    void whenGroupItemsByPersonShouldReturnSuccess() {
        CompleteOrder order = CompleteOrderDtoMock.get().toEntity();
        CompleteOrder completeOrder = new CompleteOrder(
            order.getItems(),
            order.getDeliveryTax(),
            order.getDiscount(),
            order.getAdditional());


        var itemsByPerson = service.groupItemsByPerson(completeOrder);

        assertThat(itemsByPerson, is(notNullValue()));
        assertThat(itemsByPerson.size(), is(equalTo(3)));

        assertThat(itemsByPerson, hasEntry("Artur", BigDecimal.valueOf(40)));
        assertThat(itemsByPerson, hasEntry("Maria", BigDecimal.valueOf(2)));
        assertThat(itemsByPerson, hasEntry("João", BigDecimal.valueOf(8)));

        assertThat(itemsByPerson.get("Artur"), is(equalTo(BigDecimal.valueOf(40))));
    }
}