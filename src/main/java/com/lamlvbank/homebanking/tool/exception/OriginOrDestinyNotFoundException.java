package com.lamlvbank.homebanking.tool.exception;

public class OriginOrDestinyNotFoundException extends RuntimeException {
    public OriginOrDestinyNotFoundException(String errorMessage){
        super(errorMessage);
        // super("One of the accounts involved in the operation is not available.");
    }
}
