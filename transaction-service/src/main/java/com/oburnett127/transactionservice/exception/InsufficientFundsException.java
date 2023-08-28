package com.oburnett127.transactionservice.exception;

public class InsufficientFundsException extends RuntimeException{
    public InsufficientFundsException(){
        super("Insufficient funds. Cannot complete transaction.");
    }
}

