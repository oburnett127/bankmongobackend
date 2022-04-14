package com.oburnett127.transactionservice.exceptions;

public class ZeroDepositException extends RuntimeException{
    public ZeroDepositException(){
        super("Amount to deposit must be a positive number");
    }
}