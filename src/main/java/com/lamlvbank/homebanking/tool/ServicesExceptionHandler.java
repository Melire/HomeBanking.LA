package com.lamlvbank.homebanking.tool;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.lamlvbank.homebanking.tool.exception.InsufficientBalanceException;
import com.lamlvbank.homebanking.tool.exception.OriginOrDestinyNotFoundException;

@RestControllerAdvice
public class ServicesExceptionHandler {
    @ExceptionHandler(OriginOrDestinyNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> notFoundEntitiesTransfer(OriginOrDestinyNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(ex.getMessage());
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> insufficientBalance(InsufficientBalanceException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ex.getMessage());
    }
}
