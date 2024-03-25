package com.renatoganske.backendchallenge.domain.dtos.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ErrorDTO {

    private String type;
    private LocalDateTime dateTime;
    private int value;
    private String message;

}
