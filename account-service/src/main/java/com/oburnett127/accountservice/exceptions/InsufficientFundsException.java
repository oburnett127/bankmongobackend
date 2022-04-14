package com.oburnett127.accountservice.exceptions;

public class InsufficientFundsException extends RuntimeException{
    public InsufficientFundsException(){
        super("Insufficient funds. Cannot complete transaction.");
    }
}

