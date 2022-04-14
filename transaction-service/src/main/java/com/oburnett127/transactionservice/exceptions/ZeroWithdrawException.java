package com.oburnett127.transactionservice.exceptions;

public class ZeroWithdrawException extends RuntimeException{
    public ZeroWithdrawException(){
        super("Amount to withdraw must be a positive number");
    }
}
