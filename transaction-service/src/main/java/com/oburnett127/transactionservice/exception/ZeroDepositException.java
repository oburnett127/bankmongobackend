package com.oburnett127.transactionservice.exception;

public class ZeroDepositException extends RuntimeException{
    public ZeroDepositException(){
        super("Amount to deposit must be a positive number");
    }
}