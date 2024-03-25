package com.renatoganske.backendchallenge.adapters.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BadRequestException extends RuntimeException {

    private final String error;

    public BadRequestException(String message, String error) {
        super(message);
        this.error = error;
    }
}
