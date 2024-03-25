package com.renatoganske.backendchallenge.adapters.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentException extends RuntimeException {

    private final String error;

    public PaymentException(String message, String error) {
        super(message);
        this.error = error;
    }
}
