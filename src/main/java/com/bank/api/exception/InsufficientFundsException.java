package com.bank.api.exception;

public class InsufficientFundsException extends RuntimeException {

    public InsufficientFundsException(String massage){
        super(massage);
    }
}
