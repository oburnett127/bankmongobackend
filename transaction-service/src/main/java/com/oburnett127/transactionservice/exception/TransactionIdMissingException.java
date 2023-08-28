package com.oburnett127.transactionservice.exception;

public class TransactionIdMissingException extends RuntimeException{
    public TransactionIdMissingException(String message){
        super(message);
    }
}


