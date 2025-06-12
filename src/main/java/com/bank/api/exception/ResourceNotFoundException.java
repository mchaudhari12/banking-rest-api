package com.bank.api.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String massage){
        super(massage);
    }
    
}
