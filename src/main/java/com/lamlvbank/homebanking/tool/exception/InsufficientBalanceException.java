package com.lamlvbank.homebanking.tool.exception;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(String errorMessage){
        super(errorMessage);
        // super("The origin account does not have sufficient balance to cover the operation.");
    }
}
