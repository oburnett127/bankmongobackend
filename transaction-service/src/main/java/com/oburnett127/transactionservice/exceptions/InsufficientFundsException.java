package com.oburnett127.transactionservice.exceptions;

public class InsufficientFundsException extends RuntimeException{
    public InsufficientFundsException(){
        super("Insufficient funds. Cannot complete transaction.");
    }
}

