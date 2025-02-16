package com.workintech.fswebs18challengemaven.exceptions;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


@ExceptionHandler
    public ResponseEntity<CardErrorResponse> handleException(CardException exception) {
        log.error("An error has occurred: {}", exception.getLocalizedMessage());
        CardErrorResponse response = new CardErrorResponse(exception.getMessage());
        return new ResponseEntity<>(response,exception.getHttpStatus());
    }

@ExceptionHandler
    public ResponseEntity<CardErrorResponse> handleException(Exception exception) {
        log.error("An unexpected error has occurred: {}", exception.getLocalizedMessage());
        CardErrorResponse response = new CardErrorResponse(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
