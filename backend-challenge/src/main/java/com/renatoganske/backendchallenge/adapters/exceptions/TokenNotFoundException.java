package com.renatoganske.backendchallenge.adapters.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TokenNotFoundException extends RuntimeException {

    private final String error;

    public TokenNotFoundException(String message, String error) {
        super(message);
        this.error = error;
    }
}
