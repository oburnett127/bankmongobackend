package com.oburnett127.accountservice.exception;

public class InsufficientFundsException extends RuntimeException{
    public InsufficientFundsException(){
        super("Insufficient funds. Cannot complete transaction.");
    }
}

