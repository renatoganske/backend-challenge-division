package com.renatoganske.backendchallenge.services;

import com.renatoganske.backendchallenge.domain.dtos.CompleteOrderDto;
import com.renatoganske.backendchallenge.domain.dtos.CustomerPaymentDto;
import com.renatoganske.backendchallenge.domain.dtos.DivisionResponse;
import com.renatoganske.backendchallenge.adapters.exceptions.BadRequestException;
import com.renatoganske.backendchallenge.domain.models.CompleteOrder;
import com.renatoganske.backendchallenge.domain.models.Division;
import com.renatoganske.backendchallenge.domain.models.OrderItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DivisionService {

    @Autowired
    private PaymentService paymentService;

    public DivisionResponse processDivision(final CompleteOrderDto completeOrderDto) {
        log.info("Iniciando o cálculo de divisão.");

        if (!validateCompleteOrder(completeOrderDto)) {
            throw new RuntimeException();
        }
        ;

        CompleteOrder completeOrder = completeOrderDto.toEntity();

        List<CustomerPaymentDto> valuePerCustomer = new ArrayList();

        BigDecimal totalValueToPay = completeOrder.totalValueToPay();

        Map<String, BigDecimal> itemsPerPerson = groupItemsByPerson(completeOrder);

        itemsPerPerson.entrySet()
            .stream()
            .forEach(customerWithValueToPay -> {
                BigDecimal valueToPay = calculateValueByCustomer(customerWithValueToPay.getKey(), totalValueToPay,
                    completeOrder);
                CustomerPaymentDto customerPaymentDto = CustomerPaymentDto.builder()
                    .customer(customerWithValueToPay.getKey())
                    .value(valueToPay)
                    .build();
                //desativado pois não possuo credenciais de acesso
                //PaymentInterResponseDto paymentInterResponseDto = paymentService.paymentRealize(customerPaymentDto);
                //customerPaymentDto.setLink(paymentInterResponseDto.getLocation());
                valuePerCustomer.add(customerPaymentDto);
            });

        log.info("Cálculo concluído com sucesso.");

        return Division.builder()
            .totalValueToPay(completeOrderDto.toEntity()
                .totalValueToPay())
            .valuePerCustomer(valuePerCustomer)
            .build()
            .toDivisionResponse();
    }

    private Boolean validateCompleteOrder(CompleteOrderDto completeOrderDto) {
        if (completeOrderDto.getItems()
            .isEmpty()) {
            log.error("Informe os itens comprados, seus valores e os compradores.");
            throw new BadRequestException("Informe os itens comprados, seus valores e os compradores.", "BAD_REQUEST");

        }
        if (completeOrderDto.getDeliveryTax() == null || completeOrderDto.getDeliveryTax()
            .compareTo(BigDecimal.ZERO) < 0) {
            log.error("Valor da taxa de entrega inválida.");
            throw new BadRequestException("Informe os itens comprados, seus valores e os compradores.", "BAD_REQUEST");
        }
        if (completeOrderDto.getDiscount() == null || completeOrderDto.getDiscount()
            .compareTo(BigDecimal.ZERO) < 0) {
            log.error("Valor do desconto desconto inválido.");
            throw new BadRequestException("Informe um valor de desconto que seja válido.", "BAD_REQUEST");
        }
        if (completeOrderDto.getAdditional() == null || completeOrderDto.getAdditional()
            .compareTo(BigDecimal.ZERO) < 0) {
            log.error("Valor do adicional inválido.");
            throw new BadRequestException("Informe um valor de adicional que seja válido.", "BAD_REQUEST");
        }
        return true;
    }

    public BigDecimal calculateValueByCustomer(String customer, BigDecimal totalValueToPay,
        CompleteOrder completeOrder) {
        log.info("Calculando valor a ser pago por cliente. Cliente em calculo: {}", customer);
        Map<String, BigDecimal> groupItemsByCustomer = groupItemsByPerson(completeOrder);
        BigDecimal valueByCustomer = groupItemsByCustomer.get(customer);

        BigDecimal totalValueByCustomer = groupItemsByCustomer.values()
            .stream()
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        return totalValueToPay.multiply(valueByCustomer)
            .divide(totalValueByCustomer, 2, RoundingMode.HALF_UP);
    }

    public Map<String, BigDecimal> groupItemsByPerson(CompleteOrder completeOrder) {
        log.info("Agrupando itens por cliente.");
        return completeOrder.getItems()
            .stream()
            .collect(Collectors.groupingBy(OrderItem::getCustomer,
                Collectors.reducing(BigDecimal.ZERO, OrderItem::getValue, BigDecimal::add)));
    }
}
