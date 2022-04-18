package com.oburnett127.transactionservice.exceptions;

public class TransactionIdMissingException extends RuntimeException{
    public TransactionIdMissingException(String message){
        super(message);
    }
}


