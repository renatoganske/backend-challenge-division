package com.renatoganske.backendchallenge.adapters.handler;

import com.renatoganske.backendchallenge.domain.dtos.exceptions.ErrorDTO;
import com.renatoganske.backendchallenge.adapters.exceptions.PaymentException;
import com.renatoganske.backendchallenge.adapters.exceptions.BadRequestException;
import com.renatoganske.backendchallenge.adapters.exceptions.TokenNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseBody
    @ExceptionHandler(PaymentException.class)
    public ErrorDTO handlePaymentException(PaymentException ex) {
        return new ErrorDTO("PAYMENT_ERROR",
            LocalDateTime.now(),
            HttpStatus.BAD_REQUEST.value(),
            ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(TokenNotFoundException.class)
    public ErrorDTO handleTokenNotFoundException(TokenNotFoundException ex) {
        return new ErrorDTO("TOKEN_NOT_FOUND",
            LocalDateTime.now(),
            HttpStatus.BAD_REQUEST.value(),
            ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(BadRequestException.class)
    public ErrorDTO handleBadRequestException(BadRequestException ex) {
        return new ErrorDTO("BAD_REQUEST",
            LocalDateTime.now(),
            HttpStatus.BAD_REQUEST.value(),
            ex.getMessage());
    }
}
